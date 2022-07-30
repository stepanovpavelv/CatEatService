import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations'
import { AppComponent } from './app.component';
import { CatFilterComponent } from './main/cat-filter/cat-filter.component';
import { AuthenticationComponent } from './authentication/authentication.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { HttpInterceptorService } from './services/interceptor/http-interceptor.service';
import { CatItemsListComponent } from './main/cat-items-list/cat-items-list.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MaterialModule } from './modules/material-module';
import { AppRoutingModule } from './modules/routing-module';
import { HttpErrorCatchingInterceptorService } from './services/interceptor/http-error-catching-interceptor.service';

@NgModule({
  declarations: [
    AppComponent,
    CatFilterComponent,
    AuthenticationComponent,
    CatItemsListComponent
  ],
  imports: [
    AppRoutingModule,
    MaterialModule,
    BrowserAnimationsModule,
    BrowserModule, 
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [{
    provide: HTTP_INTERCEPTORS,
    useClass: HttpInterceptorService,
    multi: true
  }, {
    provide: HTTP_INTERCEPTORS,
    useClass: HttpErrorCatchingInterceptorService,
    multi: true
  }],
  bootstrap: [AppComponent]
})
export class AppModule { }