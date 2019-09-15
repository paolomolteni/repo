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
  pageSize = 8;
  collectionSize: number;

  personIdsSelected: number[] = [];

  isNew = false;

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
    if (confirm('Cancellare la visita?')) {
      this.examinationService.deleteExamination(examination).subscribe(res => {
        this.getExamination();
      });
    }
  }

  saveDetail(): void {
    if (this.isNew) {
      this.saveMulti();
    }
    else {
      this.saveSingle();
    }
  }

  saveMulti(): void {
    if (this.personIdsSelected.length == 0) {
      alert('Valorizzare il campo utente!');
      return;
    }

    const examinationsToSave: MedicalExamination[] = [];

    this.personIdsSelected.forEach(pId => {

      const examinationToSave = new MedicalExamination();
      examinationToSave.id = this.medicalExamination.id;
      examinationToSave.personId = pId;
      examinationToSave.price = this.medicalExamination.price;
      examinationToSave.reason = this.medicalExamination.reason;
      examinationToSave.type = this.medicalExamination.type;

      const tsDate = DateUtil.getTsFromDateTimePicker(this.medicalExamination.dateCalendar, this.medicalExamination.timePicker);
      examinationToSave.tsDate = tsDate;

      examinationsToSave.push(examinationToSave);

    });

    this.examinationService.saveExaminationMulti(examinationsToSave).subscribe(res => {
      this.personIdsSelected = [];
      this.popupRef.close();
      this.getExamination();
    });
  }

  saveSingle(): void {
    if (this.medicalExamination.personId == null) {
      alert('Valorizzare il campo utente!');
      return;
    }

    const examinationToSave = new MedicalExamination();
    examinationToSave.id = this.medicalExamination.id;
    examinationToSave.personId = this.medicalExamination.personId;
    examinationToSave.price = this.medicalExamination.price;
    examinationToSave.reason = this.medicalExamination.reason;
    examinationToSave.type = this.medicalExamination.type;

    const tsDate = DateUtil.getTsFromDateTimePicker(this.medicalExamination.dateCalendar, this.medicalExamination.timePicker);
    examinationToSave.tsDate = tsDate;

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
      this.isNew = false;
      this.medicalExamination.id = examination.id;
      this.medicalExamination.reason = examination.reason;
      this.medicalExamination.price = examination.price;
      this.medicalExamination.personId = examination.personId;
      this.medicalExamination.dateCalendar = DateUtil.getDatePickerFromTs(examination.tsDate);
      this.medicalExamination.timePicker = DateUtil.getTimePickerFromTs(examination.tsDate);
      this.medicalExamination.type = examination.type;
    }
    else {
      this.isNew = true;
    }

    if (this.personId != null) {
      // From person view, show only the simple combobox
      this.isNew = false;
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

  getPerson(personId: number): Person {
    return this.peopleAvailable.find(item => {
      return item.id == personId;
    });
  }

  isExaminationDone(examination: MedicalExamination): boolean {
    const nowTs = new Date().getTime();

    if (examination.tsDate <= nowTs) {
      return true;
    }

    return false;
  }

  getDateTimeFormatted(ts: number): string {
    return DateUtil.getDateTimeFormatted(ts);
  }

}
