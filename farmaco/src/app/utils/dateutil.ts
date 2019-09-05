import { NgbDateStruct } from '@ng-bootstrap/ng-bootstrap';

export class DateUtil {
    
    static getDataFormatted(calendar: NgbDateStruct) {
        if(calendar != null) {
            return calendar.year+"-"+calendar.month+"-"+calendar.day;
        }
        else{
            return null;
        }
        
    }

    static getData(dataS: string){
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