import { NgbDateStruct, NgbTimeStruct } from '@ng-bootstrap/ng-bootstrap';

export class MedicalExamination {

    public id: number;

    public type: string;

    public reason: string;

    public price: number;

    public personId: number;

    public dateCalendar: NgbDateStruct;

    public timePicker: NgbTimeStruct;

    public tsDate: number;
}
