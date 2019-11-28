import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MaterialModule } from '../material/material.module';
import { PipelineFilterComponent } from '../activity-pipeline/pipeline-filter/pipeline-filter.component';
import { PipelineListComponent } from '../activity-pipeline/pipeline-filter/pipeline-list/pipeline-list.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {ActivityPipelineRoutingModule } from './activity-pipeline-routing.module';

@NgModule({
  declarations: [
    PipelineFilterComponent,
    PipelineListComponent
  ],
  imports: [
    CommonModule,
    MaterialModule,
    FormsModule,
    ReactiveFormsModule,
    ActivityPipelineRoutingModule
  ],
  exports: [
    PipelineFilterComponent,
    PipelineListComponent
  ]
})

export class ActivityPipelineModule { }
