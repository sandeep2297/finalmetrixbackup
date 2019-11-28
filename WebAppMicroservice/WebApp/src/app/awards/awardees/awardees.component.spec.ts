import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AwardeesComponent } from './awardees.component';

describe('AwardeesComponent', () => {
  let component: AwardeesComponent;
  let fixture: ComponentFixture<AwardeesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AwardeesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AwardeesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  // it('should create', () => {
  //   expect(component).toBeTruthy();
  // });
});
