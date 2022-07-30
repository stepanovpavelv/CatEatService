import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { filter, Subject, take } from 'rxjs';
import { takeUntil } from 'rxjs/internal/operators/takeUntil';
import { AuthService } from '../services/auth/auth.service';

@Component({
  selector: 'app-authentication',
  templateUrl: './authentication.component.html',
  styleUrls: ['./authentication.component.css']
})
export class AuthenticationComponent implements OnInit, OnDestroy {
  private readonly INDICATIONS_URL: string = '/indications';
  private readonly ngUnsubscribe$: Subject<number>;
  private readonly returnUrl: string;

  public authValid = true;
  public username = '';
  public password = '';

  constructor(private readonly router: Router, 
              private readonly _route: ActivatedRoute,
              private readonly authService: AuthService) { 
    
    this.ngUnsubscribe$ = new Subject<number>();
    this.returnUrl = this._route.snapshot.queryParams['returnUrl'] || this.INDICATIONS_URL;
  }

  ngOnInit(): void {   
    this.authService.isAuthenticated$.pipe(
      filter((isAuthenticated: boolean) => isAuthenticated),
      takeUntil(this.ngUnsubscribe$)
    ).subscribe( _ => this.router.navigateByUrl(this.returnUrl));
  }

  ngOnDestroy(): void {
    this.ngUnsubscribe$.next(0);
    this.ngUnsubscribe$.complete();      
  }

  onSubmit() {
    this.authValid = true;

    this.authService.login(this.username, this.password).pipe(
      take(1),
      takeUntil(this.ngUnsubscribe$)
    ).subscribe({
      next: _ => {
        this.authValid = true;
        this.router.navigateByUrl(this.INDICATIONS_URL);
      },
      error: _ => this.authValid = false
    });
  }
}