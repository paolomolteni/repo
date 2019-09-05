import { NgbDateStruct } from '@ng-bootstrap/ng-bootstrap';

export class Medicine {

	public date: string;

	public name: string;

	public description: string;

	public id: number;

	public dateExpiry: string;

	public dateExpiryWhenOpened: string;

	public cause: string;

	public personId: number;

	public dateCalendar: NgbDateStruct;

	public dateExpiryCalendar: NgbDateStruct;

	public dateExpiryWhenOpenedCalendar: NgbDateStruct;

	constructor() {}

}
