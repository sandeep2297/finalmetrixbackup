import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { IssuerHomeComponent } from './issuer/issuer-home/issuer-home.component';
import { AdmindashboardComponent } from './admindashboard/admindashboard.component';
import { ClientDashboardComponent } from './client/client-dashboard/client-dashboard.component';
import { ClientProfileComponent } from './client/client-profile/client-profile.component';
import { ClientProfileviewComponent } from './client/client-profile/client-profileview/client-profileview.component';
const routes: Routes = [
    {
    path: 'issuer', component: IssuerHomeComponent
  },
  { path: 'admindashboard', component: AdmindashboardComponent},
  { path: 'user/profile', component: ClientProfileComponent},
  { path: 'user/profile/edit/:id', component: ClientProfileComponent},
  { path: 'clientdashboard', component: ClientDashboardComponent},
  { path: 'user/profileview/:name', component: ClientProfileviewComponent},
  { path: 'user/profileview', component: ClientProfileviewComponent},
];
@NgModule({
    imports: [RouterModule.forChild(routes), RouterModule.forRoot(routes, {onSameUrlNavigation: 'reload'})],
    exports: [RouterModule]
  })

export class UserManagementRoutingModule { }
