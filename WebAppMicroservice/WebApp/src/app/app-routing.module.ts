import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LandingPageComponent } from './app-home/landing-page/landing-page.component';
import { LoginDialogBoxComponent } from './app-home/login-dialog-box/login-dialog-box.component';
import { IssuerHomeComponent } from './user-management/issuer/issuer-home/issuer-home.component';


const routes: Routes = [
  { path: '', component: LandingPageComponent },
  { path: 'home', component: LoginDialogBoxComponent },
  { path: 'issuer', component: IssuerHomeComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { useHash: true, onSameUrlNavigation: 'reload' })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
