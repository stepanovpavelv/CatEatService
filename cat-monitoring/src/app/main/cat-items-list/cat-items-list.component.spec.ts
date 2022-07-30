import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CatItemsListComponent } from './cat-items-list.component';

describe('CatItemsListComponent', () => {
  let component: CatItemsListComponent;
  let fixture: ComponentFixture<CatItemsListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CatItemsListComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CatItemsListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
