import { Injectable } from '@angular/core';
import { Person } from './person';
import { PEOPLE } from './mock-people';
import { Observable, of } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
	providedIn: 'root'
})
export class PeopleService {

	getPeopleUrl = 'http://localhost:8080/get/people';

  	constructor(private http: HttpClient) { }

	getPeople(): Observable<Person[]> {
  		//return PEOPLE;
		return of(PEOPLE);
	}

	getPeopleFromService(): Observable<Person[]>{
		return this.http.get<Person[]>(this.getPeopleUrl);
	}
}
