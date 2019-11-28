import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateBadgeComponent } from './create-badge.component';
import { MaterialModule } from 'src/app/material/material.module';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RouterTestingModule } from '@angular/router/testing';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { AuthenticationService } from 'src/app/app-home/authentication.service';
import { AwardsService } from '../awards.service';

describe('CreateBadgeComponent', () => {
  let component: CreateBadgeComponent;
  let fixture: ComponentFixture<CreateBadgeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreateBadgeComponent ],
      imports: [ HttpClientTestingModule, BrowserAnimationsModule,
         MaterialModule, RouterTestingModule, ReactiveFormsModule, FormsModule],
         providers: [AuthenticationService, AwardsService]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateBadgeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

});
