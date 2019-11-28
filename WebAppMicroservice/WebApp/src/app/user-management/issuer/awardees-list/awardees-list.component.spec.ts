import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AwardeesListComponent } from './awardees-list.component';

describe('AwardeesListComponent', () => {
  let component: AwardeesListComponent;
  let fixture: ComponentFixture<AwardeesListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AwardeesListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AwardeesListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

});
