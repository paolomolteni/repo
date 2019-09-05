import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { MedicalExamination } from '../model/medicalexamination';
import { Response } from '../model/response';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json',
  })
};

@Injectable({
  providedIn: 'root'
})
export class MedicalExaminationService {

  getMedicalExaminationUrl = 'http://localhost:8888/medicalexamination/list';
  getMedicalExaminationByPersonUrl = 'http://localhost:8888/medicalexamination/list/person';
  saveMedicalExaminationUrl = 'http://localhost:8888/medicalexamination/save';
  deleteMedicalExaminationUrl = 'http://localhost:8888/medicalexamination/delete';

  constructor(private http: HttpClient) { }

  getExaminations(): Observable<MedicalExamination[]> {
    return this.http.get<MedicalExamination[]>(this.getMedicalExaminationUrl);
  }

  saveExamination(medicalExamination: MedicalExamination): Observable<MedicalExamination> {
    return this.http.post<MedicalExamination>(this.saveMedicalExaminationUrl, JSON.stringify(medicalExamination), httpOptions);

  }

  deleteExamination(examination: MedicalExamination): Observable<Response<void>> {
    return this.http.put<Response<void>>(this.deleteMedicalExaminationUrl + '?examinationId=' + examination.id, null);
  }

  getExaminationByPerson(personId: number): Observable<MedicalExamination[]> {
    const options = {
      params: new HttpParams().set('personId', personId + '')
    };
    return this.http.get<MedicalExamination[]>(this.getMedicalExaminationByPersonUrl, options);
  }
}
