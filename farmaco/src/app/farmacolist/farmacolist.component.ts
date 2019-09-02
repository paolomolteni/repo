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
    this.detailInput.calendarModel = this.detailInput.getData(farmacoSelected.data);
    this.detailInput.dataScadenza = this.detailInput.getData(farmacoSelected.dataScadenza);
    this.detailInput.dataScadenzaAperto = this.detailInput.getData(farmacoSelected.dataScadenzaAperto);
    this.showDetail = true;
  }

  closeDetail(): void {
    this.showDetail = false;
  }

  saveDetail(): void {
    let data = this.detailInput.getDataFormatted(this.detailInput.calendarModel);
    let dataScadenza = this.detailInput.getDataFormatted(this.detailInput.dataScadenza);
    let dataScadenzaAperto = this.detailInput.getDataFormatted(this.detailInput.dataScadenzaAperto);

    let farmacoToSave = new Farmaco(data, this.detailInput.nomeFarmaco, this.detailInput.descrizione, dataScadenza, dataScadenzaAperto);
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
