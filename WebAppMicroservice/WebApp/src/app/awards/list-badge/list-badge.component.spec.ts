import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListBadgeComponent } from './list-badge.component';
import { MaterialModule } from 'src/app/material/material.module';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

describe('ListBadgeComponent', () => {
  let component: ListBadgeComponent;
  let fixture: ComponentFixture<ListBadgeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListBadgeComponent ],
      imports: [ MaterialModule, HttpClientTestingModule, RouterTestingModule, BrowserAnimationsModule ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListBadgeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });
});
