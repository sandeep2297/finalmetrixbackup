import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule } from '@angular/router';
import { PipelineFilterComponent } from './pipeline-filter/pipeline-filter.component';

const routes: Routes = [
  { path: 'pipelinemonitor/details', component: PipelineFilterComponent },
];



@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ActivityPipelineRoutingModule { }
