import { Injectable } from '@angular/core';
import { Router, CanActivate, UrlTree } from '@angular/router';
import { map, Observable } from 'rxjs';
import { AuthService } from '../services/auth/auth.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(private readonly authService: AuthService,
     private readonly router: Router) {}

  public canActivate(): Observable<boolean | UrlTree> {
    return this.authService.isAuthenticated$
      .pipe(
        map((s: boolean) => s ? true: this.router.parseUrl('/signin'))
      );
  }
}