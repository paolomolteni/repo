import { Component, OnInit } from '@angular/core';
import { Person } from '../model/person';
import { PersonService } from '../services/person.service';

@Component({
  selector: 'app-personlist',
  templateUrl: './personlist.component.html',
  styleUrls: ['./personlist.component.css']
})
export class PersonlistComponent implements OnInit {

  people: Person[] = [];
  showDetail: boolean;
  personInput: Person;

  constructor(private personService: PersonService) { }

  ngOnInit() {
    this.showDetail = false;
    this.getPeople();
  }

  getPeople(): void {
    this.personService.getPeople().subscribe(people => {
      this.people = people
    });

  }

  newDetail() {
    this.personInput = new Person();
    this.showDetail = true;
  }

  openDetail(person: Person) {
    this.personInput = new Person();
    this.personInput.name = person.name;
    this.personInput.lastName = person.lastName;
    this.personInput.id = person.id;
    this.showDetail = true;
  }

  closeDetail(): void {
    this.showDetail = false;
  }

  saveDetail(): void {
    let personToSave = new Person();
    personToSave.id = this.personInput.id;
    personToSave.name = this.personInput.name;
    personToSave.lastName = this.personInput.lastName;

    this.personService.savePerson(personToSave).subscribe(res => {
      this.closeDetail();
      this.getPeople();
    });

  }

  deletePerson(person: Person) {
    this.personService.deletePerson(person).subscribe(res => {
      if (res.success) {
        alert('Cancellazione avvenuto con successo!');
      }
      else {
        alert('Errore durante la cancellazione...');
      }
      this.closeDetail();
      this.getPeople();
    });
  }

}
