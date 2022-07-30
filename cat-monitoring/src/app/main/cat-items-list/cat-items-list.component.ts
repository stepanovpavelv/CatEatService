import { HttpErrorResponse } from '@angular/common/http';
import { ChangeDetectionStrategy, ChangeDetectorRef, Component, OnDestroy, OnInit } from '@angular/core';
import { map, Subject, takeUntil } from 'rxjs';
import { IndicationService } from 'src/app/services/indication/indication.service';
import { CatUtils } from 'src/app/utils/cat-utils';
import { RangeDate } from '../cat-filter/cat-range-filter';
import { CatViewItem } from '../cat-view-item';

@Component({
  selector: 'app-cat-items-list',
  templateUrl: './cat-items-list.component.html',
  styleUrls: ['./cat-items-list.component.css'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class CatItemsListComponent implements OnInit, OnDestroy {

  private readonly ngUnsubscribe$: Subject<number>;
  private range: RangeDate;
  
  readonly displayedColumns: string[] = ['rowNumber', 'date', 'value'];
  catRows: CatViewItem[] = [];

  constructor(
    private readonly indicationService: IndicationService,
    private readonly cdr: ChangeDetectorRef) {

    this.ngUnsubscribe$ = new Subject<number>();
    this.range = CatUtils.getCurrentMonth();
  }

  ngOnInit(): void {
    this.readCatIndications();
  }

  ngOnDestroy(): void {
    this.ngUnsubscribe$.next(0);
    this.ngUnsubscribe$.complete();      
  }

  changeDateFilter($event: RangeDate) : void {
    this.range = $event;
    this.readCatIndications();
  }

  readCatIndications() {
    this.indicationService.getIndicationsByDates(this.range)
        .pipe(
          map(response => response.map((item, index) => { return {
            rowNumber: index + 1,
            date: CatUtils.convertToDateLocal(item.date),
            value: item.value
          }})),
          takeUntil(this.ngUnsubscribe$)
        )
        .subscribe({
          next: (items: CatViewItem[]) => {
            this.catRows = items;
            this.cdr.markForCheck();
          },
          error: (error: HttpErrorResponse) => { console.log(error.status + ' ' + error.message) } 
        });
  }
}