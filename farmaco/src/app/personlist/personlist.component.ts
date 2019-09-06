import { Component, OnInit } from '@angular/core';
import { Person } from '../model/person';
import { PersonService } from '../services/person.service';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-personlist',
  templateUrl: './personlist.component.html',
  styleUrls: ['./personlist.component.css']
})
export class PersonlistComponent implements OnInit {

  people: Person[] = [];
  showMedicines: boolean;
  personInput: Person;
  popupRef: NgbModalRef;
  
  page = 1;
  pageSize = 4;
  collectionSize: number;

  constructor(private personService: PersonService, private modalService: NgbModal) { }

  ngOnInit() {
    this.showMedicines = false;
    this.getPeople();
  }

  getPeople(): void {
    this.personService.getPeople().subscribe(people => {
      this.resetPagination();
      this.people = people;
    });

  }

  openTableMedicines(person: Person) {
    this.personInput = new Person();
    this.personInput.name = person.name;
    this.personInput.lastName = person.lastName;
    this.personInput.id = person.id;
    this.showMedicines = true;
  }

  closePopup(): void {
    this.popupRef.close();
  }

  closeTableMedicines() {
    this.showMedicines = false;
  }

  savePerson(): void {
    let personToSave = new Person();
    personToSave.id = this.personInput.id;
    personToSave.name = this.personInput.name;
    personToSave.lastName = this.personInput.lastName;

    this.personService.savePerson(personToSave).subscribe(res => {
      this.closePopup();
      this.popupRef.close();
      this.getPeople();
    });

  }

  deletePerson(person: Person) {
    if (confirm('Cancellare l\'utene?')) {
      this.personService.deletePerson(person).subscribe(res => {
        this.closePopup();
        this.getPeople();
      });
    }
  }

  openPopup(longContent, person: Person) {
    this.personInput = new Person();

    if (person != null) {
      this.personInput.name = person.name;
      this.personInput.lastName = person.lastName;
      this.personInput.id = person.id;
    }

    this.popupRef = this.modalService.open(longContent, { scrollable: true });
  }
  
  get personPaged(): Person[] {
    this.collectionSize = this.people.length;
    return this.people.slice((this.page - 1) * this.pageSize, (this.page - 1) * this.pageSize + this.pageSize);
  }

  resetPagination() {
    this.page = 1;
  }

}
