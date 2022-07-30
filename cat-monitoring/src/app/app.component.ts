import { Component, OnDestroy, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subject, takeUntil } from 'rxjs';
import { AuthService } from './services/auth/auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit, OnDestroy {
  public title = 'Cat monitoring';
  public isAuthenticated = false;
  private ngUnsubscribe$ = new Subject<void>();

  constructor(private router: Router,
    private readonly authService: AuthService) { }

  public ngOnInit(): void {
    this.authService.isAuthenticated$.pipe(
      takeUntil(this.ngUnsubscribe$)
    ).subscribe((isAuthenticated: boolean) => this.isAuthenticated = isAuthenticated);
  }

  public ngOnDestroy(): void {
    this.ngUnsubscribe$.next();
    this.ngUnsubscribe$.complete();
  }

  public logout(): void {
    this.authService.logout();
    this.router.navigate(['/']);
  }
}