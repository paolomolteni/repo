import { NgbDateStruct, NgbTimeStruct } from '@ng-bootstrap/ng-bootstrap';

export class DateUtil {

    static getTsFromDateTimePicker(datePicker: NgbDateStruct, timePicker: NgbTimeStruct): number {
        let hour = 0;
        let minute = 0;

        if (timePicker != null) {
            hour = timePicker.hour;
            minute = timePicker.minute;
        }

        const date = new Date(datePicker.year, datePicker.month - 1, datePicker.day, hour, minute, 0, 0);
        //const offset = date.getTimezoneOffset() * 60 * 1000;
        //return (date.getTime() - offset);
        return date.getTime();
    }

    static getDateFromTs(ts: number) {
        const date = new Date(ts);
        return date;
    }

    static getDateTimeFormatted(ts: number) {
        const d = this.getDateFromTs(ts);
        return d.getDate()+"/"+(d.getMonth() + 1)+"/"+d.getFullYear()+" "+d.getHours()+":"+d.getMinutes()+":0";
    }

    static getDateFormatted(ts: number) {
        const d = this.getDateFromTs(ts);
        return d.getDate()+"/"+(d.getMonth() + 1)+"/"+d.getFullYear();
    }

    static getTimePickerFromTs(ts: number): NgbTimeStruct {
        const d = this.getDateFromTs(ts);
        return {
            hour: d.getHours(),
            minute: d.getMinutes(),
            second: 0
        };
    }

    static getDatePickerFromTs(ts: number): NgbDateStruct {
        const d = this.getDateFromTs(ts);
        return {
            day: d.getDate(),
            month: d.getMonth() + 1,
            year: d.getFullYear()
        };
    }

}