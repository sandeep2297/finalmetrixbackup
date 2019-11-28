import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ClientProfileComponent } from './client-profile.component';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';

import { MaterialModule } from 'src/app/material/material.module';
import { MatFormFieldModule } from '@angular/material';

import { HttpClientTestingModule } from '@angular/common/http/testing';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RouterTestingModule } from '@angular/router/testing';
import { By } from '@angular/platform-browser';
import { ClientProfileviewComponent } from './client-profileview/client-profileview.component';

describe('ClientProfileComponent', () => {
  let component: ClientProfileComponent;
  let fixture: ComponentFixture<ClientProfileComponent>;
  let e1: HTMLElement;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ClientProfileComponent, ClientProfileviewComponent],
      imports: [RouterTestingModule, MaterialModule, MatFormFieldModule,
        ReactiveFormsModule, FormsModule, HttpClientTestingModule, BrowserAnimationsModule]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ClientProfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('form invalid when empty', () => {
    expect(component.newClient.valid).toBeFalsy();
  });

  it('should call the submitEvent method', async(() => {
    fixture.detectChanges();
    spyOn(component, 'submitEvent');
    e1 = fixture.debugElement.query(By.css('button')).nativeElement;
    e1.click();
    expect(component.submitEvent).toHaveBeenCalledTimes(0);
  }));

  it('name field validity', () => {
    const name = component.newClient.controls.profileName;
    expect(name.valid).toBeFalsy();
    name.setValue('');
    expect(name.hasError('required')).toBeTruthy();
  });

  it('email field validity', () => {
    const email = component.newClient.controls.orgEmail;
    expect(email.valid).toBeFalsy();
    email.setValue('');
    expect(email.hasError('required')).toBeTruthy();
  });

  it('organisation name field validity', () => {
    const orgName = component.newClient.controls.organisationName;
    expect(orgName.valid).toBeFalsy();
    orgName.setValue('');
    expect(orgName.hasError('required')).toBeTruthy();
  });


});

