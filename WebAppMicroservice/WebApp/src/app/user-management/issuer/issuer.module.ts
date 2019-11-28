import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MaterialModule } from '../../material/material.module';
import { IssuerRoutingModule } from './issuer-routing.module';
import { IssuerHomeComponent } from './issuer-home/issuer-home.component';
import { PipelineSettingsComponent } from './pipeline-settings/pipeline-settings.component';
import { RulesComponent } from './rules/rules.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { IssuerProfileComponent } from './issuer-profile/issuer-profile.component';
import { AddIssuerComponent } from './add-issuer/add-issuer.component';
import { FlexLayoutModule } from '@angular/flex-layout';
import { RuleListItemComponent } from './profile-home/rule-list-item/rule-list-item.component';
import { ViewRuleComponent } from './profile-home/view-rule/view-rule.component';
import { ProfileHomeComponent } from './profile-home/profile-home.component';
import { ViewPipelineComponent } from './view-pipeline/view-pipeline.component';
import { AwardIssuersComponent } from './award-issuers/award-issuers.component';
import { PublishComponent } from './publish/publish.component';
import { PipelineMonitorComponent } from './pipeline-monitor/pipeline-monitor.component';
import { AwardeesListComponent } from './awardees-list/awardees-list.component';
import { ConfirmationAndConvertComponent } from './confirmation-and-convert/confirmation-and-convert.component';
import { RevocationDialogComponent } from './revocation-dialog/revocation-dialog.component';
import { ConfigRuleHelpComponent } from './rules/config-rule-help/config-rule-help.component';
import { MatTooltipModule } from '@angular/material';
import { ConfirmComponent } from './confirm/confirm.component';
import { RuleModalComponent } from './profile-home/rule-list-item/rule-modal/rule-modal.component';
import { ViewIssuerBadgesComponent } from './rules/view-issuer-badges/view-issuer-badges.component';
import { AwardManuallyComponent } from './award-manually/award-manually.component';
import { ViewAndVerifyComponent } from './view-and-verify/view-and-verify.component';

@NgModule({
  declarations: [IssuerHomeComponent,
    PipelineSettingsComponent,
    RulesComponent,
    IssuerProfileComponent,
    AddIssuerComponent,
    RuleListItemComponent,
    ViewRuleComponent,
    ProfileHomeComponent,
    ViewPipelineComponent,
    AwardIssuersComponent,
    PublishComponent,
    PipelineMonitorComponent,
    AwardeesListComponent,
    ConfirmationAndConvertComponent,
    RevocationDialogComponent,
    ConfigRuleHelpComponent,
    ConfirmComponent,
    RuleModalComponent,
    AwardManuallyComponent,
    ViewAndVerifyComponent,
    ViewIssuerBadgesComponent],
  imports: [
    CommonModule,
    IssuerRoutingModule,
    MaterialModule,
    FormsModule,
    ReactiveFormsModule,
    FlexLayoutModule,
    MatTooltipModule
  ],
  entryComponents: [AddIssuerComponent,
                    ConfigRuleHelpComponent,
                    ConfirmComponent,
                    RuleModalComponent,
                    RevocationDialogComponent,
                    ConfirmationAndConvertComponent,
                    ViewAndVerifyComponent,
                    ViewIssuerBadgesComponent],
  providers: [IssuerHomeComponent]
})
export class IssuerModule { }
