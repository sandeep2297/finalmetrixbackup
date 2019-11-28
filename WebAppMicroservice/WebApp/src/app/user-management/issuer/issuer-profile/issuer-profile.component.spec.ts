import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { IssuerProfileComponent } from './issuer-profile.component';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { IssuerDetailsInputService } from '../issuer-details-input.service';
import { RouterModule } from '@angular/router';

describe('IssuerProfileComponent', () => {
  let component: IssuerProfileComponent;
  let fixture: ComponentFixture<IssuerProfileComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [IssuerProfileComponent],
      imports: [HttpClientTestingModule,
        RouterModule.forRoot([]) ],
      providers: [IssuerDetailsInputService ]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(IssuerProfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });
});
