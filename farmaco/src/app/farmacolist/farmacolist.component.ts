import { Component, OnInit, Input } from '@angular/core';
import { Medicine } from '../model/medicine';
import { MedicineDetailInput } from '../model/medicinedetailinput';
import { FarmacoService } from '../farmaco.service';
import { ActivatedRoute } from '@angular/router';
import { PersonService } from '../services/person.service';
import { Person } from '../model/person';

@Component({
  selector: 'app-farmacolist',
  templateUrl: './farmacolist.component.html',
  styleUrls: ['./farmacolist.component.css']
})
export class FarmacolistComponent implements OnInit {

  medicines: Medicine[] = [];
  showDetail: boolean;
  detailInput: MedicineDetailInput;
  peopleAvailable: Person[] = [];

  @Input()
  personId: number;

  constructor(private farmacoService: FarmacoService, private personService: PersonService) { }

  ngOnInit() {
    this.detailInput = new MedicineDetailInput();
    this.showDetail = false;
    this.getMedicines();
    this.getPeople();
  }

  ngOnChanges() {
    this.ngOnInit();
  }

  newDetail(): void {
    this.detailInput = new MedicineDetailInput();
    this.showDetail = true;
  }

  openDetail(medicineSelected: Medicine): void {
    this.detailInput = new MedicineDetailInput();
    this.detailInput.id = medicineSelected.id;
    this.detailInput.name = medicineSelected.name;
    this.detailInput.description = medicineSelected.description;
    this.detailInput.date = this.detailInput.getData(medicineSelected.date);
    this.detailInput.dateExpiry = this.detailInput.getData(medicineSelected.dateExpiry);
    this.detailInput.dateExpiryWhenOpened = this.detailInput.getData(medicineSelected.dateExpiryWhenOpened);
    this.detailInput.cause = medicineSelected.cause;
    this.showDetail = true;
  }

  closeDetail(): void {
    this.showDetail = false;
  }

  saveDetail(): void {
    let date = this.detailInput.getDataFormatted(this.detailInput.date);
    let dateExpiry = this.detailInput.getDataFormatted(this.detailInput.dateExpiry);
    let dateExpiryWhenOpened = this.detailInput.getDataFormatted(this.detailInput.dateExpiryWhenOpened);

    let medicineToSave = new Medicine(date, this.detailInput.name, this.detailInput.description, dateExpiry, dateExpiryWhenOpened, this.detailInput.cause, this.personId);
    medicineToSave.id = this.detailInput.id;
    this.farmacoService.saveFarmaco(medicineToSave).subscribe(res => {
      this.closeDetail();
      this.getMedicines();
    });
    
  }

  getMedicines(): void {
    // Check if a person was selected
    if(this.personId != null){
      this.farmacoService.getMedicinesByPerson(this.personId).subscribe(medicines => {
        this.medicines = medicines
      });
    }
    else{
      this.farmacoService.getFarmaci().subscribe(medicines => {
        this.medicines = medicines
      });
    }

  }

  getPeople() {
    this.personService.getPeople().subscribe(people => {
      this.peopleAvailable = people;
    });
  }

}
