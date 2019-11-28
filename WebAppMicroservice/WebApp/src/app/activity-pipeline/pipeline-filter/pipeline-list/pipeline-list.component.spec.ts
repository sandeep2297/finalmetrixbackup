import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PipelineListComponent } from './pipeline-list.component';
import { MaterialModule } from '../../../material/material.module';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

describe('PipelineListComponent', () => {
  let component: PipelineListComponent;
  let fixture: ComponentFixture<PipelineListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [PipelineListComponent],
      imports: [MaterialModule, HttpClientTestingModule, BrowserAnimationsModule]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PipelineListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  // it('should create', () => {
  //   expect(component).toBeTruthy();
  // });
});
