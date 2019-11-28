import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewPipelineComponent } from './view-pipeline.component';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { RouterModule } from '@angular/router';


describe('ViewPipelineComponent', () => {
  let component: ViewPipelineComponent;
  let fixture: ComponentFixture<ViewPipelineComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ViewPipelineComponent],
      imports: [HttpClientTestingModule,
        RouterModule.forRoot([])],
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewPipelineComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });
});
