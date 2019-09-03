import {NgbDateStruct} from '@ng-bootstrap/ng-bootstrap';

export class MedicineDetailInput {

    date: NgbDateStruct;

    name: string;

    description: string;

    id: number;

    dateExpiry: NgbDateStruct

    dateExpiryWhenOpened: NgbDateStruct;

    cause: string;

    constructor() {}
    
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
