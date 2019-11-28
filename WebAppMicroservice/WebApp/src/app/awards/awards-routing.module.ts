import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ListBadgeComponent } from './list-badge/list-badge.component';
import { CreateBadgeComponent } from './create-badge/create-badge.component';
import { AwardeesComponent } from './awardees/awardees.component';
import { PublicBadgeComponent } from './public-badge/public-badge.component';
import { AwardBadgeComponent } from './award-badge/award-badge.component';
import { ViewBadgeComponent } from './view-badge/view-badge.component';

const routes: Routes = [
  { path: 'awards/badge', component: ListBadgeComponent },
  { path: 'awards/badge/create', component: CreateBadgeComponent },
  { path: 'awards/badge/view', component: ViewBadgeComponent },
  { path: 'awards/badge/edit/:id', component: CreateBadgeComponent },
  { path: 'awards/badge/:id/public-badge', component: PublicBadgeComponent },
  { path: 'awards/badge/:id/details', component: AwardeesComponent },
  { path: 'awards/badge/:id/award', component: AwardBadgeComponent }
];


@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AwardsRoutingModule { }
