import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PipelineMonitorComponent } from './pipeline-monitor.component';

describe('PipelineMonitorComponent', () => {
  let component: PipelineMonitorComponent;
  let fixture: ComponentFixture<PipelineMonitorComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PipelineMonitorComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PipelineMonitorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
