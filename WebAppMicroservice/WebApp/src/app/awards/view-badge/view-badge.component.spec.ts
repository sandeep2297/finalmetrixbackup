import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewBadgeComponent } from './view-badge.component';

describe('ViewBadgeComponent', () => {
  let component: ViewBadgeComponent;
  let fixture: ComponentFixture<ViewBadgeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewBadgeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewBadgeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });
});
