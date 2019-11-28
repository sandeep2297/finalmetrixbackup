import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PublicBadgeComponent } from './public-badge.component';

describe('PublicBadgeComponent', () => {
  let component: PublicBadgeComponent;
  let fixture: ComponentFixture<PublicBadgeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PublicBadgeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PublicBadgeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  // it('should create', () => {
  //   expect(component).toBeTruthy();
  // });
});
