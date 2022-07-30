import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CatItemsListComponent } from '../main/cat-items-list/cat-items-list.component';
import { AuthenticationComponent } from '../authentication/authentication.component';
import { HomeComponent } from '../home/home.component';
import { AuthGuard } from '../authentication/auth-guard';

const routes: Routes = [
  {
    path: 'indications',
    component: CatItemsListComponent,
    canActivate: [ AuthGuard ]
  },
  {
    path: 'signin',
    component: AuthenticationComponent
  },
  {
    path: '',
    component: HomeComponent
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }