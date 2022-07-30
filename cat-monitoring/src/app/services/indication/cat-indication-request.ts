export class CatIndicationRequest {
    startDate: Date;
    finishDate: Date;

    constructor(startDate: Date, finishDate: Date) {
        this.startDate = startDate;
        this.finishDate = finishDate;
    }
}