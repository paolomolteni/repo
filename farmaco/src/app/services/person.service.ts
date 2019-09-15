import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { Person } from '../model/person';
import { Response } from '../model/response';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json',
  })
};

@Injectable({
  providedIn: 'root'
})
export class PersonService {

  getPersonUrl = 'http://localhost:9090/medicine/person/list';
  savePersonUrl = 'http://localhost:9090/medicine/person/save';
  deletePersonUrl = 'http://localhost:9090/medicine/person/delete';

  constructor(private http: HttpClient) { }

  getPeople(): Observable<Person[]> {
    return this.http.get<Person[]>(this.getPersonUrl);
  }

  savePerson(person: Person): Observable<Person> {
    return this.http.post<Person>(this.savePersonUrl, JSON.stringify(person), httpOptions);

  }

  deletePerson(person: Person): Observable<Response<void>> {
    return this.http.put<Response<void>>(this.deletePersonUrl + '?personId=' + person.id, null);
  }

}
