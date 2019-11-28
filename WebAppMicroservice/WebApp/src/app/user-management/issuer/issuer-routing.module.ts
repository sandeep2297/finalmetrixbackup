import { NgModule, Component } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { IssuerHomeComponent } from './issuer-home/issuer-home.component';
import { PipelineSettingsComponent } from './pipeline-settings/pipeline-settings.component';
import { RulesComponent } from './rules/rules.component';
import { IssuerProfileComponent } from './issuer-profile/issuer-profile.component';
import { ViewRuleComponent } from './profile-home/view-rule/view-rule.component';
import { ProfileHomeComponent } from './profile-home/profile-home.component';
import { ViewPipelineComponent } from './view-pipeline/view-pipeline.component';

import { AddIssuerComponent } from './add-issuer/add-issuer.component';
import { AwardIssuersComponent } from './award-issuers/award-issuers.component';
import { PublishComponent } from './publish/publish.component';
import { AwardeesListComponent } from './awardees-list/awardees-list.component';
import { PipelineMonitorComponent } from './pipeline-monitor/pipeline-monitor.component';
// import { AwardBadgeComponent } from 'src/app/awards/award-badge/award-badge.component';
import { AwardManuallyComponent } from './award-manually/award-manually.component';


const routes: Routes = [
  { path: 'issuer', component: IssuerHomeComponent },
  { path: 'issuer/:issuerid', component: ProfileHomeComponent,
   children: [
   {
    path: 'profile', component: IssuerProfileComponent
   },
   {
    path: 'awards', component: AwardeesListComponent
   },
   {
    path: 'monitor', component: PipelineMonitorComponent
   },
   {
     path: 'pipeline', component: PipelineSettingsComponent
   },
   {
     path: 'pipeline/:pipelineid', component: ViewPipelineComponent
   },
   {
    path: 'badges', component: AwardIssuersComponent
   },
   {
     path: 'pipeline/:pipelineid/rules', component: RulesComponent
   },
   {
     path: 'pipeline/:pipelineid/rules/:ruleid', component: ViewRuleComponent
   },
   {
    path: 'pipeline/:pipelineid/rules/:ruleid/edit', component: RulesComponent
   },
   {
    path: 'pipeline/:pipelineid/publish', component: PublishComponent
   },
   {
    path: 'badges/:badgeid/award', component: AwardManuallyComponent
   },
  ]
},
  { path: 'add', component: AddIssuerComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes), RouterModule.forRoot(routes, { onSameUrlNavigation: 'reload' })],
  exports: [RouterModule]
})
export class IssuerRoutingModule { }
