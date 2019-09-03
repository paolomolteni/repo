import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { Medicine } from './model/medicine';
import { HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class FarmacoService {

  getFarmacoUrl = 'http://localhost:8888/medicine/list';
  saveFarmacoUrl = 'http://localhost:8888/medicine/save';

  constructor(private http: HttpClient) { }

	getFarmaci(): Observable<Medicine[]>{
		return this.http.get<Medicine[]>(this.getFarmacoUrl);
  }

  saveFarmaco(medicine: Medicine): Observable<Medicine> {

    let httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json',
      })
    };

    return this.http.post<Medicine>(this.saveFarmacoUrl, JSON.stringify(medicine), httpOptions);

  }


}
