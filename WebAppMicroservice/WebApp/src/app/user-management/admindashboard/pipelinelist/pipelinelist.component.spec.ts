import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PipelinelistComponent } from './pipelinelist.component';
import { PipelineListComponent } from 'src/app/activity-pipeline/pipeline-filter/pipeline-list/pipeline-list.component';
import { PipelineFilterComponent } from 'src/app/activity-pipeline/pipeline-filter/pipeline-filter.component';

describe('PipelinelistComponent', () => {
  let component: PipelinelistComponent;
  let fixture: ComponentFixture<PipelinelistComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PipelinelistComponent, PipelineListComponent, PipelineFilterComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PipelinelistComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  // it('should create', () => {
  //   expect(component).toBeTruthy();
  // });
});
