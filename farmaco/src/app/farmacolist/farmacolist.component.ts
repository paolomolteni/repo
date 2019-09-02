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
    this.farmaci.push(new Farmaco('30/08/2019', 'Farmaco1', '2 volte al giorno'));
    this.farmaci.push(new Farmaco('20/08/2019', 'Farmaco2', '3 volte al giorno'));
  }

  newDetail(): void {
    this.detailInput = new FarmacoDetailInput();
    this.showDetail = true;
  }

  openDetail(farmacoSelected: Farmaco): void {
    this.detailInput = new FarmacoDetailInput();
    this.detailInput.nomeFarmaco = farmacoSelected.nomeFarmaco;
    this.detailInput.descrizione = farmacoSelected.descrizione;
    this.detailInput.calendarModel.day = +farmacoSelected.data.split("/")[0];
    this.detailInput.calendarModel.month = +farmacoSelected.data.split("/")[1];
    this.detailInput.calendarModel.year = +farmacoSelected.data.split("/")[2];

    this.showDetail = true;
  }

  closeDetail(): void {
    this.showDetail = false;
  }

  saveDetail(): void {
    this.farmaci.push(new Farmaco(this.detailInput.getDataFormatted(), this.detailInput.nomeFarmaco, this.detailInput.descrizione));
    this.closeDetail();
  }

  // getFarmaci(): void {
	// 	this.peopleService.getPeopleFromService().subscribe(people => {
	// 		this.people = people
	// 	});

	// }

}
