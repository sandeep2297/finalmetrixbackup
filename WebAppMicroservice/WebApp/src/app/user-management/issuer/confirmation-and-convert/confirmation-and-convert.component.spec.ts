import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ConfirmationAndConvertComponent } from './confirmation-and-convert.component';

describe('ConfirmationAndConvertComponent', () => {
  let component: ConfirmationAndConvertComponent;
  let fixture: ComponentFixture<ConfirmationAndConvertComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ConfirmationAndConvertComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ConfirmationAndConvertComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

});
