import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { CatUtils } from 'src/app/utils/cat-utils';
import { RangeDate } from './cat-range-filter';

@Component({
  selector: 'app-cat-filter',
  templateUrl: './cat-filter.component.html',
  styleUrls: ['./cat-filter.component.css']
})
export class CatFilterComponent implements OnInit {
  range = new FormGroup({
    start: new FormControl<Date | null>(null),
    end: new FormControl<Date | null>(null),
  });

  @Output()
  onFilterChange: EventEmitter<RangeDate> = new EventEmitter();

  constructor() { }

  ngOnInit(): void {
    this.range.setValue(CatUtils.getCurrentMonth())
  }

  filterByDates(): void {
    let emittedRange = {
      start: this.range.value.start ?? CatUtils.getCurrentMonth().start,
      end: this.range.value.end ?? CatUtils.getCurrentMonth().end
    };

    this.onFilterChange.emit(emittedRange);
  }
}