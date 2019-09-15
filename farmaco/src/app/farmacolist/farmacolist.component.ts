import { Component, OnInit, Input } from '@angular/core';
import { Medicine } from '../model/medicine';
import { FarmacoService } from '../services/farmaco.service';
import { PersonService } from '../services/person.service';
import { Person } from '../model/person';
import { DateUtil } from '../utils/dateutil';
import { NgbModalRef, NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-farmacolist',
  templateUrl: './farmacolist.component.html',
  styleUrls: ['./farmacolist.component.css']
})
export class FarmacolistComponent implements OnInit {

  medicines: Medicine[] = [];
  medicine: Medicine;
  peopleAvailable: Person[] = [];

  @Input()
  personId: number;

  popupRef: NgbModalRef;

  page = 1;
  pageSize = 8;
  collectionSize: number;

  personIdsSelected: number[] = [];

  isNew = false;

  constructor(private farmacoService: FarmacoService, private personService: PersonService, private modalService: NgbModal) { }

  ngOnInit() {
    this.medicine = new Medicine();
    this.getMedicines();
    this.getPeople();
  }

  ngOnChanges() {
    this.ngOnInit();
  }

  saveDetail(): void {
    if (this.isNew) {
      this.saveMulti();
    } 
    else {
      this.saveSingle();
    }
  }

  saveMulti(): void {
    if (this.personIdsSelected.length == 0) {
      alert('Valorizzare il campo utente!');
      return;
    }

    const medicinesToSave: Medicine[] = [];

    this.personIdsSelected.forEach(pId => {
      let medicineToSave = new Medicine();
      medicineToSave.tsDate = DateUtil.getTsFromDateTimePicker(this.medicine.dateCalendar, null);
      medicineToSave.name = this.medicine.name;
      medicineToSave.description = this.medicine.description;

      if(this.medicine.dateExpiryCalendar != null){
        medicineToSave.tsDateExpiry = DateUtil.getTsFromDateTimePicker(this.medicine.dateExpiryCalendar, null);
      }

      if(this.medicine.dateExpiryWhenOpenedCalendar != null){
        medicineToSave.tsDateExpiryWhenOpened = DateUtil.getTsFromDateTimePicker(this.medicine.dateExpiryWhenOpenedCalendar, null);
      }

      medicineToSave.cause = this.medicine.cause;
      medicineToSave.personId = pId;
      medicineToSave.id = this.medicine.id;

      medicinesToSave.push(medicineToSave);
    });

    this.farmacoService.saveFarmacoMulti(medicinesToSave).subscribe(res => {
      this.personIdsSelected = [];
      this.popupRef.close();
      this.getMedicines();
    });
  }

  saveSingle(): void {
    if (this.medicine.personId == null) {
      alert('Valorizzare il campo utente!');
      return;
    }

    let medicineToSave = new Medicine();
    medicineToSave.tsDate = DateUtil.getTsFromDateTimePicker(this.medicine.dateCalendar, null);
    medicineToSave.name = this.medicine.name;
    medicineToSave.description = this.medicine.description;
    
    if(this.medicine.dateExpiryCalendar != null){
      medicineToSave.tsDateExpiry = DateUtil.getTsFromDateTimePicker(this.medicine.dateExpiryCalendar, null);
    }
    else {
      medicineToSave.tsDateExpiry = null;
    }

    if(this.medicine.dateExpiryWhenOpenedCalendar != null){
      medicineToSave.tsDateExpiryWhenOpened = DateUtil.getTsFromDateTimePicker(this.medicine.dateExpiryWhenOpenedCalendar, null);
    }
    else{
      medicineToSave.tsDateExpiryWhenOpened = null;
    }

    medicineToSave.cause = this.medicine.cause;
    medicineToSave.personId = this.medicine.personId;
    medicineToSave.id = this.medicine.id;

    this.farmacoService.saveFarmaco(medicineToSave).subscribe(res => {
      this.popupRef.close();
      this.getMedicines();
    });
  }

  getMedicines(): void {
    // Check if a person was selected
    if(this.personId != null){
      this.farmacoService.getMedicinesByPerson(this.personId).subscribe(medicines => {
        this.resetPagination();
        this.medicines = medicines
      });
    }
    else{
      this.farmacoService.getFarmaci().subscribe(medicines => {
        this.resetPagination();
        this.medicines = medicines
      });
    }

  }

  getPeople() {
    this.personService.getPeople().subscribe(people => {
      this.peopleAvailable = people;
    });
  }

  deleteMedicine(medicine: Medicine) {
    if (confirm('Cancellare il farmaco?')) {
      this.farmacoService.deleteMedicine(medicine).subscribe(res => {
        this.getMedicines();
      });
    }
  }

  closePopup(): void {
    this.popupRef.close();
  }

  openPopup(longContent, medicineSelected: Medicine) {
    this.medicine = new Medicine();

    if (medicineSelected != null) {
      this.isNew = false;
      this.medicine = new Medicine();
      this.medicine.id = medicineSelected.id;
      this.medicine.name = medicineSelected.name;
      this.medicine.description = medicineSelected.description;
      this.medicine.dateCalendar = DateUtil.getDatePickerFromTs(medicineSelected.tsDate);

      if(medicineSelected.tsDateExpiry != null){
        this.medicine.dateExpiryCalendar = DateUtil.getDatePickerFromTs(medicineSelected.tsDateExpiry);
      }

      if(medicineSelected.tsDateExpiryWhenOpened != null){
        this.medicine.dateExpiryWhenOpenedCalendar = DateUtil.getDatePickerFromTs(medicineSelected.tsDateExpiryWhenOpened);
      }

      this.medicine.cause = medicineSelected.cause;
      this.medicine.personId = medicineSelected.personId;
    }
    else {
      this.isNew = true;
    }

    if (this.personId != null) {
      // From person view, show only the simple combobox
      this.isNew = false;
      this.medicine.personId = this.personId;
    }

    this.popupRef = this.modalService.open(longContent, { scrollable: false });
  }

  get medicinePaged(): Medicine[] {
    this.collectionSize = this.medicines.length;
    return this.medicines.slice((this.page - 1) * this.pageSize, (this.page - 1) * this.pageSize + this.pageSize);
  }

  resetPagination() {
    this.page = 1;
  }

  getPerson(personId: number): Person {
    return this.peopleAvailable.find(item => {
      return item.id == personId;
    });
  }

  isMedicineExpired(medicine: Medicine): boolean {
    const nowTs = new Date().getTime();

    if (medicine.tsDateExpiry != null) {
      if (medicine.tsDateExpiry <= nowTs) {
        return true;
      }
    }

    if (medicine.tsDateExpiryWhenOpened != null) {
      if (medicine.tsDateExpiryWhenOpened <= nowTs) {
        return true;
      }

    }

    return false;
  }

  getDateFormatted(ts: number): string {
    if(ts != null){
      return DateUtil.getDateFormatted(ts);
    }
    return "";
  }

}
