import { NgbDateStruct } from '@ng-bootstrap/ng-bootstrap';

export class Medicine {

	public tsDate: number;

	public name: string;

	public description: string;

	public id: number;

	public tsDateExpiry: number;

	public tsDateExpiryWhenOpened: number;

	public cause: string;

	public personId: number;

	public dateCalendar: NgbDateStruct;

	public dateExpiryCalendar: NgbDateStruct;

	public dateExpiryWhenOpenedCalendar: NgbDateStruct;

	constructor() {}

}
