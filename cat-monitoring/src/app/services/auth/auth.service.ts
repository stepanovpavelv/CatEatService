import { Injectable, OnDestroy } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { BehaviorSubject, Observable, Subject } from 'rxjs';
import { CommonServiceSettings } from '../service-settings';
import { takeUntil } from 'rxjs/internal/operators/takeUntil';
import { map } from 'rxjs/internal/operators/map';
import { AuthRequest } from './auth-request';
import { AuthResponse } from './auth-response';
import { UserCredentials } from './user-credentials';

@Injectable({
  providedIn: 'root'
})
export class AuthService implements OnDestroy {
  private readonly CURRENT_USER: string = 'currentUser';
  private readonly IS_LOGGEDIN: string = 'isLoggedIn';

  private readonly ngUnsubscribe$: Subject<number>;
  private readonly userItemSubject: BehaviorSubject<UserCredentials | null>;
  private readonly authSub$: BehaviorSubject<boolean>;

  constructor(private readonly http: HttpClient) { 
    let curUserStorage = localStorage.getItem(this.CURRENT_USER);
    if (curUserStorage) {
      this.userItemSubject = new BehaviorSubject<UserCredentials | null>(JSON.parse(curUserStorage));
    } else {
      this.userItemSubject = new BehaviorSubject<UserCredentials | null>(null);
    }
    let isLogged = localStorage.getItem(this.IS_LOGGEDIN);
    this.authSub$ = new BehaviorSubject<boolean>(isLogged === 'true');
    this.ngUnsubscribe$ = new Subject();
  }

  public get isAuthenticated$(): Observable<boolean> {
    return this.authSub$.asObservable();
  }

  login(username: string, password: string) : Observable<UserCredentials | null> {
    let body = new AuthRequest(username, password);
    this.http.post<AuthResponse>(CommonServiceSettings.loginUrl, body)
        .pipe(
          map(item => item.token),
          takeUntil(this.ngUnsubscribe$)
        )
        .subscribe(token => {
          let user = new UserCredentials(username, password, token);
          localStorage.setItem(this.CURRENT_USER, JSON.stringify(user));
          localStorage.setItem(this.IS_LOGGEDIN, 'true');
          this.userItemSubject?.next(user)
          this.authSub$.next(true);
        });

    return this.userItemSubject.asObservable();
  }

  getCurrentUser() : UserCredentials | null {
    return this.userItemSubject?.getValue();
  }

  logout() {
    localStorage.removeItem(this.CURRENT_USER);
    localStorage.removeItem(this.IS_LOGGEDIN);
    this.userItemSubject.next(null);
    this.authSub$.next(false);
  }

  ngOnDestroy(): void {
    this.ngUnsubscribe$.next(0);
    this.ngUnsubscribe$.complete();
    this.authSub$.next(false);
    this.authSub$.complete();
  }
}