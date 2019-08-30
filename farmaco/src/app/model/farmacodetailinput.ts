import {NgbDateStruct} from '@ng-bootstrap/ng-bootstrap';

export class FarmacoDetailInput {

    calendarModel: NgbDateStruct;

    nomeFarmaco: string;

    descrizione: string;

    constructor() {
        this.calendarModel = {
            day:0,
            month:0,
            year:0
        };
    }
    
    public getDataFormatted() {
        return this.calendarModel.day+"/"+this.calendarModel.month+"/"+this.calendarModel.year;
    }
}
