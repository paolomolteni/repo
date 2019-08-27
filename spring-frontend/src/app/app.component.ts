import { Component } from '@angular/core';
import { Person } from './person';
import { PEOPLE } from './mock-people';
import { PeopleService } from './people.service';

@Component({
	selector: 'app-root',
  	templateUrl: './app.component.html',
  	styleUrls: ['./app.component.css']
})
export class AppComponent {

	title = 'People list';
	people : Person[];

	constructor(private peopleService: PeopleService) { }

	ngOnInit() {
  		this.getPeople();
	}

	onSelect(person: Person): void {
		alert(person.id);
  	}

	getPeople(): void {
  		//this.people = this.peopleService.getPeople();
		
		/*this.peopleService.getPeople().subscribe(people => {
			this.people = people
		});*/

		this.peopleService.getPeopleFromService().subscribe(people => {
			this.people = people
		});

	}
}
