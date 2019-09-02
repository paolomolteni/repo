import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { Farmaco } from './model/farmaco';

@Injectable({
  providedIn: 'root'
})
export class FarmacoService {

  getFarmacoUrl = 'http://localhost:8888/farmaco/list';

  constructor(private http: HttpClient) { }

	getFarmaci(): Observable<Farmaco[]>{
		return this.http.get<Farmaco[]>(this.getFarmacoUrl);
	}
}
