import { Injectable, OnDestroy } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Subject } from 'rxjs/internal/Subject';
import { RangeDate } from 'src/app/main/cat-filter/cat-range-filter';
import { CommonServiceSettings } from '../service-settings';
import { CatIndicationRequest } from './cat-indication-request';
import { map, Observable, takeUntil } from 'rxjs';
import { CatIndicationResponse } from './cat-indication-response';

@Injectable({
  providedIn: 'root'
})
export class IndicationService implements OnDestroy {

  private readonly ngUnsubscribe$: Subject<number>;

  constructor(private readonly http: HttpClient) {
    this.ngUnsubscribe$ = new Subject();
  }

  getIndicationsByDates(range: RangeDate): Observable<CatIndicationResponse[]> {
    let dto: CatIndicationRequest = this.createDateDto(range.start, range.end);
    return this.http.post<CatIndicationResponse[]>(CommonServiceSettings.indicationDatesUrl, dto)
            .pipe(
              takeUntil(this.ngUnsubscribe$)
            );
  }

  ngOnDestroy(): void {
    this.ngUnsubscribe$.next(0);
    this.ngUnsubscribe$.complete();          
  }

  private createDateDto(firstDate: Date, secondDate: Date) : CatIndicationRequest {
    return new CatIndicationRequest(firstDate, secondDate);
  }
}
