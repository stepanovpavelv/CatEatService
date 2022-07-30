import { Injectable } from '@angular/core';
import { HttpErrorResponse, HttpEvent, HttpHandler, HttpHeaders, HttpInterceptor, HttpRequest } from '@angular/common/http'
import { Observable } from 'rxjs/internal/Observable';
import { AuthService } from '../auth/auth.service';
import { CommonServiceSettings } from '../service-settings';

@Injectable({
  providedIn: 'root'
})
export class HttpInterceptorService implements HttpInterceptor {

  constructor(private readonly authService: AuthService) { }
  
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    let url = req.url;
    if (url.includes(CommonServiceSettings.loginUrl)) {
      return next.handle(req);
    }

    let currentUser = this.authService.getCurrentUser();
    if (!currentUser) {
      throw new HttpErrorResponse({
          error: 'Current user can not be null',
          headers: req.headers,
          status: 401,
          statusText: 'Authentication error',
          url: req.url
      });
    }

    let request = req.clone({
      headers: new HttpHeaders().set('Authorization', 'Bearer ' + currentUser.token)
    });
    return next.handle(request);
  }
}