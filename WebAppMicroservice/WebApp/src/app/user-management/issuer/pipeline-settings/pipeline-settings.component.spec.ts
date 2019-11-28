import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PipelineSettingsComponent } from './pipeline-settings.component';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material';
import { By } from '@angular/platform-browser';
import { MaterialModule } from 'src/app/material/material.module';
import { RouterTestingModule } from '@angular/router/testing';

describe('PipelineSettingsComponent', () => {
  let component: PipelineSettingsComponent;
  let fixture: ComponentFixture<PipelineSettingsComponent>;
  let e1: HTMLElement;
  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [PipelineSettingsComponent],
      imports: [HttpClientTestingModule, BrowserAnimationsModule, ReactiveFormsModule, FormsModule,
        MatFormFieldModule, MaterialModule, RouterTestingModule]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PipelineSettingsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
  it('form invalid when empty', () => {
    expect(component.pipeline.valid).toBeFalsy();
  });
  it('until the details are filled do not call the function submitData', async(() => {
    fixture.detectChanges();
    spyOn(component, 'submitData');
    e1 = fixture.debugElement.query(By.css('button')).nativeElement;
    e1.click();
    expect(component.submitData).toHaveBeenCalledTimes(0);
  }));
  it('name field validity', () => {
    const name = component.pipeline.controls.pipelineName;
    expect(name.valid).toBeFalsy();
    name.setValue('');
    expect(name.hasError('required')).toBeTruthy();
  });
  it('description field validity', () => {
    const description = component.pipeline.controls.pipelineDescription;
    expect(description.valid).toBeFalsy();
    description.setValue('');
    expect(description.hasError('required')).toBeTruthy();
  });
});
