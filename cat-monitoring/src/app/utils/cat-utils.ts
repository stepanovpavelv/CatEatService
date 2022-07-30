import { Injectable } from "@angular/core";
import { RangeDate } from "../main/cat-filter/cat-range-filter";

@Injectable()
export class CatUtils {
    public static getCurrentMonth() : RangeDate {
        let currentDate = new Date();
        return {
          start: new Date(currentDate.getFullYear(), currentDate.getMonth(), 1),
          end: currentDate
        }
    }

    public static convertToDateLocal(date: Date) : Date {
        let newDate = new Date(date);
        let offsetMinutes = newDate.getTimezoneOffset();

        newDate.setMinutes(newDate.getMinutes() + offsetMinutes)
        return newDate;
    }
}