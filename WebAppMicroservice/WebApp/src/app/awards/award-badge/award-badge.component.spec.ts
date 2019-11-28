import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AwardBadgeComponent } from './award-badge.component';

describe('AwardBadgeComponent', () => {
  let component: AwardBadgeComponent;
  let fixture: ComponentFixture<AwardBadgeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AwardBadgeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AwardBadgeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  // it('should create', () => {
  //   expect(component).toBeTruthy();
  // });
});
