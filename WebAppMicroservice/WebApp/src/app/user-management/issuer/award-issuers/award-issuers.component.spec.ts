import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AwardIssuersComponent } from './award-issuers.component';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { IssueAwardsService } from '../issue-awards.service';
import { AuthenticationService } from 'src/app/app-home/authentication.service';
import { MaterialModule } from 'src/app/material/material.module';
import { RouterTestingModule } from '@angular/router/testing';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

describe('AwardIssuersComponent', () => {
  let component: AwardIssuersComponent;
  let fixture: ComponentFixture<AwardIssuersComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AwardIssuersComponent ],
      imports: [HttpClientTestingModule, MaterialModule, RouterTestingModule, BrowserAnimationsModule],
      providers: [IssueAwardsService, AuthenticationService]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AwardIssuersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
