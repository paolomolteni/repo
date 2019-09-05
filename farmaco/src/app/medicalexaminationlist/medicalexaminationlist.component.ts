import { Component, OnInit, Input } from '@angular/core';
import { MedicalExamination } from '../model/medicalexamination';
import { Person } from '../model/person';
import { MedicalExaminationService } from '../services/medicalexamination.service';
import { PersonService } from '../services/person.service';
import { DateUtil } from '../utils/dateutil';

@Component({
  selector: 'app-medicalexaminationlist',
  templateUrl: './medicalexaminationlist.component.html',
  styleUrls: ['./medicalexaminationlist.component.css']
})
export class MedicalexaminationlistComponent implements OnInit {

  examinations: MedicalExamination[] = [];
  showDetail: boolean;
  medicalExamination: MedicalExamination;
  peopleAvailable: Person[] = [];

  @Input()
  personId: number;

  constructor(private examinationService: MedicalExaminationService, private personService: PersonService) { }

  ngOnInit() {
    this.medicalExamination = new MedicalExamination();
    this.showDetail = false;
    this.getExamination();
    this.getPeople();
  }

  ngOnChanges() {
    this.ngOnInit();
  }

  getExamination(): void {
    // Check if a person was selected
    if (this.personId != null) {
      this.examinationService.getExaminationByPerson(this.personId).subscribe(examinations => {
        this.examinations = examinations;
      });
    }
    else {
      this.examinationService.getExaminations().subscribe(examinations => {
        this.examinations = examinations;
      });
    }

  }

  getPeople() {
    this.personService.getPeople().subscribe(people => {
      this.peopleAvailable = people;
    });
  }

  deleteExamination(examination: MedicalExamination) {
    this.examinationService.deleteExamination(examination).subscribe(res => {
      this.closeDetail();
      this.getExamination();
      if (res.success) {
        alert('Cancellazione avvenuto con successo!');
      }
      else {
        alert('Errore durante la cancellazione...');
      }
    });
  }

  closeDetail(): void {
    this.showDetail = false;
  }

  newDetail(): void {
    this.medicalExamination = new MedicalExamination();
    this.showDetail = true;
  }

  openDetail(examinationSelected: MedicalExamination): void {
    this.medicalExamination = new MedicalExamination();
    this.medicalExamination.id = examinationSelected.id;
    this.medicalExamination.reason = examinationSelected.reason;
    this.medicalExamination.price = examinationSelected.price;
    this.medicalExamination.personId = examinationSelected.personId;
    this.medicalExamination.dateCalendar = DateUtil.getData(examinationSelected.date);
    this.showDetail = true;
  }

  saveDetail(): void {
    const examinationToSave = new MedicalExamination();
    examinationToSave.id = this.medicalExamination.id;
    examinationToSave.personId = this.personId;
    examinationToSave.price = this.medicalExamination.price;
    examinationToSave.reason = this.medicalExamination.reason;
    examinationToSave.date = DateUtil.getDataFormatted(this.medicalExamination.dateCalendar);

    this.examinationService.saveExamination(examinationToSave).subscribe(res => {
      this.closeDetail();
      this.getExamination();
    });

  }

}
