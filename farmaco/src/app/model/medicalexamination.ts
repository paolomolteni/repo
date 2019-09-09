import { NgbDateStruct } from '@ng-bootstrap/ng-bootstrap';

export class MedicalExamination {

    public id: number;

    public date: string;

    public type: string;

    public reason: string;

    public price: number;

    public personId: number;

    public dateCalendar: NgbDateStruct;
}