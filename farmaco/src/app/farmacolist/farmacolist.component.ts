import { Component, OnInit } from '@angular/core';
import { Medicine } from '../model/medicine';
import { MedicineDetailInput } from '../model/medicinedetailinput';
import { FarmacoService } from '../farmaco.service';

@Component({
  selector: 'app-farmacolist',
  templateUrl: './farmacolist.component.html',
  styleUrls: ['./farmacolist.component.css']
})
export class FarmacolistComponent implements OnInit {

  medicines: Medicine[] = [];
  showDetail: boolean;
  detailInput: MedicineDetailInput;

  constructor(private farmacoService: FarmacoService) { }

  ngOnInit() {
    this.detailInput = new MedicineDetailInput();
    this.showDetail = false;
    this.getMedicines();
  }

  newDetail(): void {
    this.detailInput = new MedicineDetailInput();
    this.showDetail = true;
  }

  openDetail(medicineSelected: Medicine): void {
    this.detailInput = new MedicineDetailInput();
    this.detailInput.id = medicineSelected.id;
    this.detailInput.name = medicineSelected.name;
    this.detailInput.description = medicineSelected.description;
    this.detailInput.date = this.detailInput.getData(medicineSelected.date);
    this.detailInput.dateExpiry = this.detailInput.getData(medicineSelected.dateExpiry);
    this.detailInput.dateExpiryWhenOpened = this.detailInput.getData(medicineSelected.dateExpiryWhenOpened);
    this.showDetail = true;
  }

  closeDetail(): void {
    this.showDetail = false;
  }

  saveDetail(): void {
    let date = this.detailInput.getDataFormatted(this.detailInput.date);
    let dateExpiry = this.detailInput.getDataFormatted(this.detailInput.dateExpiry);
    let dateExpiryWhenOpened = this.detailInput.getDataFormatted(this.detailInput.dateExpiryWhenOpened);

    let medicineToSave = new Medicine(date, this.detailInput.name, this.detailInput.description, dateExpiry, dateExpiryWhenOpened);
    medicineToSave.id = this.detailInput.id;
    this.farmacoService.saveFarmaco(medicineToSave).subscribe(res => {
      this.closeDetail();
      this.getMedicines();
    });
    
  }

  getMedicines(): void {
    this.farmacoService.getFarmaci().subscribe(medicines => {
      this.medicines = medicines
    });

  }

}
