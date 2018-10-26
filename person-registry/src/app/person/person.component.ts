import { Component, OnInit, Input } from '@angular/core';

import { Person } from '../person';
import { Car } from '../car';
import { Response } from '../response';

import { PersonService } from '../person.service';

@Component({
  selector: 'app-person',
  templateUrl: './person.component.html',
  styleUrls: ['./person.component.css']
})
export class PersonComponent implements OnInit {
	
	@Input() person: Person;
	@Input() car: Car = new Car();
	
	@Input() name:string;
	@Input() lastName:string;
	@Input() age:number;
	
	people:Person[];
	personSelected:Person;

	constructor(private personService: PersonService) { }

	ngOnInit() {
		this.getAllPerson();
	}
	
	add(): void{
		let p = new Person();
		p.name = this.name;
		p.lastName = this.lastName;
		p.age = this.age;
		this.personService.addPerson(p).subscribe(reponse => {

			this.getAllPerson();
			
		});
	}
	
	getAllPerson() : void{
		
		this.personService.getAllPerson().subscribe(response => {
			
			this.people = response.items;

		});
	}
	
	getDetail(personSel: Person): void{
				
		this.personService.getPerson(personSel.id).subscribe(response => {
			
			this.personSelected = response.item;
			
		});
		
	}
	
	addCar(): void{

		this.personService.addCar(this.car, this.personSelected.id).subscribe(reponse => {

			this.getDetail(this.personSelected);
			
		});
	}

}
