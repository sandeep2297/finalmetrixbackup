import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddIssuerComponent } from './add-issuer.component';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { MatFormFieldModule, MatDialogRef, MatDialogModule, MAT_DIALOG_DATA } from '@angular/material';
import { MaterialModule } from 'src/app/material/material.module';
import { RouterTestingModule } from '@angular/router/testing';
import { By } from '@angular/platform-browser';
import { ChangeDetectionStrategy } from '@angular/core';

describe('AddIssuerComponent', () => {
  let component: AddIssuerComponent;
  let fixture: ComponentFixture<AddIssuerComponent>;
  // let e1: HTMLElement;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [AddIssuerComponent],
      imports: [HttpClientTestingModule, BrowserAnimationsModule, ReactiveFormsModule, FormsModule,
        MatFormFieldModule, MaterialModule, RouterTestingModule, MatDialogModule],
      providers: [
        {
          provide: MatDialogRef,
          useValue: {}
        },
        {
          provide: MAT_DIALOG_DATA,
          useValue: {} // Add any data you wish to test if it is passed/used correctly
        }
      ]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddIssuerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  // it('should create', () => {
  //   expect(component).toBeTruthy();
  // });
  // it('form invalid when empty', () => {
  //   expect(component.issuerForm.valid).toBeFalsy();
  // });
  // it('until the details are filled do not call the function formSubmit', async(() => {
  //   fixture.detectChanges();
  //   spyOn(component, 'formSubmit');
  //   fixture.detectChanges();
  //   e1 = fixture.debugElement.query(By.css('button')).nativeElement;
  //   fixture.detectChanges();
  //   e1.click();
  //   fixture.detectChanges();
  //   expect(component.formSubmit).toHaveBeenCalledTimes(0);
  // }));
  // it('should render title in a h1 tag', () => {
  //   fixture = TestBed.createComponent(AddIssuerComponent);
  //   fixture.detectChanges();
  //   const compiled = fixture.debugElement.nativeElement;
  //   expect(compiled.querySelector('h1').textContent).toContain('ISSUER DETAILS');
  // });
});
