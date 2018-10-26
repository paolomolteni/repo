import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { Person } from './person';
import { Car } from './car';
import { Response } from './response';

const httpOptions = {
	headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class PersonService {
	
	private personCreationUrl = 'http://localhost:8080/people-registry/api/person/create';
	private getPersonsUrl = 'http://localhost:8080/people-registry/api/person/get/all';
	private getPersonUrl = 'http://localhost:8080/people-registry/api/person/get';
	private carCreationUrl = 'http://localhost:8080/people-registry/api/car/create';

	constructor(private http: HttpClient) { }
	
	addPerson(p:Person) : Observable<Response<Person>>{
		return this.http.post<Response<Person>>(this.personCreationUrl, p, httpOptions);
	}
	
	getAllPerson() : Observable<Response<Person>>{
		return this.http.get<Response<Person>>(this.getPersonsUrl);
	}
	
	getPerson(id:number) : Observable<Response<Person>>{
		return this.http.get<Response<Person>>(this.getPersonUrl + "?id=" + id);
	}
	
	addCar(car: Car, personId: number) : Observable<Response<Person>>{
		return this.http.post<Response<Person>>(this.carCreationUrl + "?personId=" + personId, car);
	}
	
}
