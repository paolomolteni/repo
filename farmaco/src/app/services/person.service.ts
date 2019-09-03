import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { Person } from '../model/person';

@Injectable({
  providedIn: 'root'
})
export class PersonService {

  getPersonUrl = 'http://localhost:8888/person/list';
  savePersonUrl = 'http://localhost:8888/person/save';

  constructor(private http: HttpClient) { }

  getPeople(): Observable<Person[]> {
    return this.http.get<Person[]>(this.getPersonUrl);
  }

  savePerson(person: Person): Observable<Person> {

    let httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json',
      })
    };

    return this.http.post<Person>(this.savePersonUrl, JSON.stringify(person), httpOptions);

  }

}
