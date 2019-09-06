import { Component, OnInit, Input } from '@angular/core';
import { MedicalExamination } from '../model/medicalexamination';
import { Person } from '../model/person';
import { MedicalExaminationService } from '../services/medicalexamination.service';
import { PersonService } from '../services/person.service';
import { DateUtil } from '../utils/dateutil';
import { NgbModalRef, NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-medicalexaminationlist',
  templateUrl: './medicalexaminationlist.component.html',
  styleUrls: ['./medicalexaminationlist.component.css']
})
export class MedicalexaminationlistComponent implements OnInit {

  examinations: MedicalExamination[] = [];
  medicalExamination: MedicalExamination;
  peopleAvailable: Person[] = [];
  popupRef: NgbModalRef;

  @Input()
  personId: number;

  page = 1;
  pageSize = 4;
  collectionSize: number;

  constructor(private examinationService: MedicalExaminationService, private personService: PersonService, private modalService: NgbModal) { }

  ngOnInit() {
    this.medicalExamination = new MedicalExamination();
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
        this.resetPagination();
        this.examinations = examinations;
      });
    }
    else {
      this.examinationService.getExaminations().subscribe(examinations => {
        this.resetPagination();
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
      this.getExamination();
      if (res.success) {
        alert('Cancellazione avvenuto con successo!');
      }
      else {
        alert('Errore durante la cancellazione...');
      }
    });
  }

  saveDetail(): void {
    const examinationToSave = new MedicalExamination();
    examinationToSave.id = this.medicalExamination.id;
    examinationToSave.personId = this.medicalExamination.personId;
    examinationToSave.price = this.medicalExamination.price;
    examinationToSave.reason = this.medicalExamination.reason;
    examinationToSave.date = DateUtil.getDataFormatted(this.medicalExamination.dateCalendar);

    this.examinationService.saveExamination(examinationToSave).subscribe(res => {
      this.popupRef.close();
      this.getExamination();
    });

  }

  closePopup(): void {
    this.popupRef.close();
  }

  openPopup(longContent, examination: MedicalExamination) {
    this.medicalExamination = new MedicalExamination();

    if (examination != null) {
      this.medicalExamination.id = examination.id;
      this.medicalExamination.reason = examination.reason;
      this.medicalExamination.price = examination.price;
      this.medicalExamination.personId = examination.personId;
      this.medicalExamination.dateCalendar = DateUtil.getData(examination.date);
    }

    if(this.personId != null){
      this.medicalExamination.personId = this.personId;
    }

    this.popupRef = this.modalService.open(longContent, { scrollable: false });
  }

  get examinationsPaged(): MedicalExamination[] {
    this.collectionSize = this.examinations.length;
    return this.examinations.slice((this.page - 1) * this.pageSize, (this.page - 1) * this.pageSize + this.pageSize);
  }

  resetPagination() {
    this.page = 1;
  }

}
