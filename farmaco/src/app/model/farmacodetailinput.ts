import {NgbDateStruct} from '@ng-bootstrap/ng-bootstrap';

export class FarmacoDetailInput {

    calendarModel: NgbDateStruct;

    nomeFarmaco: string;

    descrizione: string;

    id: number;

    dataScadenza: NgbDateStruct

    dataScadenzaAperto: NgbDateStruct;

    constructor() {
        /*this.calendarModel = {
            day:0,
            month:0,
            year:0
        };

        this.dataScadenza = {
            day:0,
            month:0,
            year:0
        };

        this.dataScadenzaAperto = {
            day:0,
            month:0,
            year:0
        };*/
    }
    
    public getDataFormatted(calendar: NgbDateStruct) {
        if(calendar != null){
            return calendar.year+"-"+calendar.month+"-"+calendar.day;
        }
        else{
            return null;
        }
        
    }

    public getData(dataS: string){
        if(dataS != null && dataS.length > 0){
            return {
                day: +dataS.split("-")[2],
                month: +dataS.split("-")[1],
                year: +dataS.split("-")[0]
            };
        }
        
        return null;
    }
    
}
