import { TestBed } from '@angular/core/testing';

import { HttpErrorCatchingInterceptorService } from './http-error-catching-interceptor.service';

describe('HttpErrorCatchingInterceptorService', () => {
  let service: HttpErrorCatchingInterceptorService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(HttpErrorCatchingInterceptorService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
