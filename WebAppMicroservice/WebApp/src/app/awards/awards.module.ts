import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CreateBadgeComponent } from './create-badge/create-badge.component';
import { MaterialModule } from '../material/material.module';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ListBadgeComponent } from './list-badge/list-badge.component';
import { FlexLayoutModule } from '@angular/flex-layout';
import { ViewBadgeComponent } from './view-badge/view-badge.component';
import { AwardsRoutingModule } from './awards-routing.module';
import { AwardBadgeComponent } from './award-badge/award-badge.component';
import { AwardeesComponent } from './awardees/awardees.component';
import { RevocationDialogBoxComponent } from './revocation-dialog-box/revocation-dialog-box.component';
import { PublicBadgeComponent } from './public-badge/public-badge.component';
import { ConfirmComponent } from '../user-management/issuer/confirm/confirm.component';


@NgModule({
  declarations: [CreateBadgeComponent, ListBadgeComponent, ViewBadgeComponent, AwardBadgeComponent, AwardeesComponent, RevocationDialogBoxComponent, PublicBadgeComponent],
  imports: [
    CommonModule,
    MaterialModule,
    ReactiveFormsModule,
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    FlexLayoutModule,
    AwardsRoutingModule
  ],
  exports: [CreateBadgeComponent],
  entryComponents: [ViewBadgeComponent, RevocationDialogBoxComponent, ConfirmComponent]
})
export class AwardsModule { }
