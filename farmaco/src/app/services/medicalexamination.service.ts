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

  getMedicalExaminationUrl = 'http://localhost:9090/medicine/medicalexamination/list';
  getMedicalExaminationByPersonUrl = 'http://localhost:9090/medicine/medicalexamination/list/person';
  saveMedicalExaminationUrl = 'http://localhost:9090/medicine/medicalexamination/save';
  deleteMedicalExaminationUrl = 'http://localhost:9090/medicine/medicalexamination/delete';
  saveMedicalExaminationMultiUrl = 'http://localhost:9090/medicine/medicalexamination/save/multi';

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

  saveExaminationMulti(examinations: MedicalExamination[]): Observable<Response<void>> {
    return this.http.post<Response<void>>(this.saveMedicalExaminationMultiUrl, JSON.stringify(examinations), httpOptions);
  }
}
