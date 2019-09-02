import { Component, OnInit } from '@angular/core';
import { Farmaco } from '../model/farmaco';
import { FarmacoDetailInput } from '../model/farmacodetailinput';
import { FarmacoService } from '../farmaco.service';

@Component({
  selector: 'app-farmacolist',
  templateUrl: './farmacolist.component.html',
  styleUrls: ['./farmacolist.component.css']
})
export class FarmacolistComponent implements OnInit {

  farmaci: Farmaco[] = [];
  showDetail: boolean;
  detailInput: FarmacoDetailInput;

  constructor(private farmacoService: FarmacoService) { }

  ngOnInit() {
    this.detailInput = new FarmacoDetailInput();
    this.showDetail = false;
    this.getFarmaci();
  }

  newDetail(): void {
    this.detailInput = new FarmacoDetailInput();
    this.showDetail = true;
  }

  openDetail(farmacoSelected: Farmaco): void {
    this.detailInput = new FarmacoDetailInput();
    this.detailInput.id = farmacoSelected.id;
    this.detailInput.nomeFarmaco = farmacoSelected.nomeFarmaco;
    this.detailInput.descrizione = farmacoSelected.descrizione;
    this.detailInput.calendarModel.day = +farmacoSelected.data.split("-")[2];
    this.detailInput.calendarModel.month = +farmacoSelected.data.split("-")[1];
    this.detailInput.calendarModel.year = +farmacoSelected.data.split("-")[0];

    this.showDetail = true;
  }

  closeDetail(): void {
    this.showDetail = false;
  }

  saveDetail(): void {
    let farmacoToSave = new Farmaco(this.detailInput.getDataFormatted(), this.detailInput.nomeFarmaco, this.detailInput.descrizione);
    farmacoToSave.id = this.detailInput.id;
    this.farmacoService.saveFarmaco(farmacoToSave).subscribe(res => {
      this.closeDetail();
      this.getFarmaci();
    });
    
  }

  getFarmaci(): void {
    this.farmacoService.getFarmaci().subscribe(farmaci => {
      this.farmaci = farmaci
    });

  }

}
