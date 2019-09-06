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
  pageSize = 4;
  collectionSize: number;

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

    if (this.medicine.personId == null) {
      alert('Valorizzare il campo utente!');
      return;
    }

    let medicineToSave = new Medicine();
    medicineToSave.date = DateUtil.getDataFormatted(this.medicine.dateCalendar);
    medicineToSave.name = this.medicine.name;
    medicineToSave.description = this.medicine.description;
    medicineToSave.dateExpiry = DateUtil.getDataFormatted(this.medicine.dateExpiryCalendar);
    medicineToSave.dateExpiryWhenOpened = DateUtil.getDataFormatted(this.medicine.dateExpiryWhenOpenedCalendar);
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
      this.medicine = new Medicine();
      this.medicine.id = medicineSelected.id;
      this.medicine.name = medicineSelected.name;
      this.medicine.description = medicineSelected.description;
      this.medicine.dateCalendar = DateUtil.getData(medicineSelected.date);
      this.medicine.dateExpiryCalendar = DateUtil.getData(medicineSelected.dateExpiry);
      this.medicine.dateExpiryWhenOpenedCalendar = DateUtil.getData(medicineSelected.dateExpiryWhenOpened);
      this.medicine.cause = medicineSelected.cause;
      this.medicine.personId = medicineSelected.personId;
    }

    if(this.personId != null){
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

}
