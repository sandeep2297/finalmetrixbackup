import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { PipelineFilterComponent } from './pipeline-filter.component';
import { PipelineListComponent } from './pipeline-list/pipeline-list.component';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { MaterialModule } from 'src/app/material/material.module';
import { MatFormFieldModule } from '@angular/material';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RouterTestingModule } from '@angular/router/testing';

describe('PipelineFilterComponent', () => {
  let component: PipelineFilterComponent;
  let fixture: ComponentFixture<PipelineFilterComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [PipelineFilterComponent, PipelineListComponent],
      imports: [MaterialModule,
        ReactiveFormsModule,
        FormsModule,
        MatFormFieldModule, HttpClientTestingModule,
        RouterTestingModule, BrowserAnimationsModule]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PipelineFilterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
