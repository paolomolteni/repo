import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'farmaco';
  collapsed = true;
  showMedicineList = false;
  showPersonList = false;
  showExaminationList = false;

  openPersonDetail() {
    this.showMedicineList = false;
    this.showPersonList = true;
    this.showExaminationList = false;
  }

  openMedicineDetail() {
    this.showMedicineList = true;
    this.showPersonList = false;
    this.showExaminationList = false;
  }

  openExamination() {
    this.showMedicineList = false;
    this.showPersonList = false;
    this.showExaminationList = true;
  }
}
