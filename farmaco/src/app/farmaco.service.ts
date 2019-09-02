import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { Farmaco } from './model/farmaco';
import { HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class FarmacoService {

  getFarmacoUrl = 'http://localhost:8888/farmaco/list';
  saveFarmacoUrl = 'http://localhost:8888/farmaco/save';

  constructor(private http: HttpClient) { }

	getFarmaci(): Observable<Farmaco[]>{
		return this.http.get<Farmaco[]>(this.getFarmacoUrl);
  }

  saveFarmaco(farmaco: Farmaco): Observable<Farmaco> {

    let httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json',
      })
    };

    return this.http.post<Farmaco>(this.saveFarmacoUrl, JSON.stringify(farmaco), httpOptions);

  }


}
