export class CatIndicationResponse {
    id: number;
    date: Date;
    value: number;

    constructor(id: number, date: Date, value: number) {
        this.id = id;
        this.date = date;
        this.value = value;
    }
}