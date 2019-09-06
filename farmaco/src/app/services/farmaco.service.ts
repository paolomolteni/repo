import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { Medicine } from '../model/medicine';
import { HttpHeaders } from '@angular/common/http';
import { Response } from '../model/response';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json',
  })
};

@Injectable({
  providedIn: 'root'
})
export class FarmacoService {

  getFarmacoUrl = 'http://localhost:8080/medicine/medicine/list';
  saveFarmacoUrl = 'http://localhost:8080/medicine/medicine/save';
  getFarmacoByPersonUrl = 'http://localhost:8080/medicine/medicine/list/person';
  deleteMedicneUrl = 'http://localhost:8080/medicine/medicine/delete';

  constructor(private http: HttpClient) { }

  getFarmaci(): Observable<Medicine[]> {
    return this.http.get<Medicine[]>(this.getFarmacoUrl);
  }

  saveFarmaco(medicine: Medicine): Observable<Medicine> {
    return this.http.post<Medicine>(this.saveFarmacoUrl, JSON.stringify(medicine), httpOptions);
  }

  getMedicinesByPerson(personId: number): Observable<Medicine[]> {
    const options = {
      params: new HttpParams().set('personId', personId + '')
    };
    return this.http.get<Medicine[]>(this.getFarmacoByPersonUrl, options);
  }

  deleteMedicine(medicie: Medicine): Observable<Response<void>> {
    return this.http.put<Response<void>>(this.deleteMedicneUrl + '?medicineId=' + medicie.id, null);
  }

}
