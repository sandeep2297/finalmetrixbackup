import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ClientDashboardComponent } from './client/client-dashboard/client-dashboard.component';
import { ClientProfileComponent } from './client/client-profile/client-profile.component';
import { IssuerModule } from './issuer/issuer.module';
import { MaterialModule } from '../material/material.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AddIssuerComponent } from './issuer/add-issuer/add-issuer.component';
import { AdmindashboardComponent } from './admindashboard/admindashboard.component';
import { ClientProfileviewComponent } from './client/client-profile/client-profileview/client-profileview.component';
import { UserManagementRoutingModule } from './user-management-routing.module';
import { ChartsModule } from 'ng2-charts';
import { ActivityPipelineModule } from '../activity-pipeline/activity-pipeline.module';
import { IssuerlistComponent } from './admindashboard/issuerlist/issuerlist.component';
import { PipelinelistComponent } from './admindashboard/pipelinelist/pipelinelist.component';
import { UpdateclientComponent } from './client/client-profile/updateclient/updateclient.component';
import { ViewIssuerBadgesComponent } from './issuer/rules/view-issuer-badges/view-issuer-badges.component';
import { TermsDialogBoxComponent } from './client/terms-dialog-box/terms-dialog-box.component';

@NgModule({
  declarations: [
    ClientDashboardComponent, ClientProfileComponent, AdmindashboardComponent, ClientProfileviewComponent, IssuerlistComponent, PipelinelistComponent, UpdateclientComponent, TermsDialogBoxComponent,
  ],
  imports: [
    ActivityPipelineModule,
    CommonModule,
    MaterialModule,
    FormsModule,
    ReactiveFormsModule,
    IssuerModule,
    UserManagementRoutingModule,
    ChartsModule,
  ],
  exports: [
    ClientDashboardComponent, ClientProfileComponent,
  ],
  entryComponents: [
    AddIssuerComponent, IssuerlistComponent, PipelinelistComponent, UpdateclientComponent, ViewIssuerBadgesComponent, TermsDialogBoxComponent]

})
export class UserManagementModule { }
