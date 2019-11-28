import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RuleModalComponent } from './rule-modal.component';

describe('RuleModalComponent', () => {
  let component: RuleModalComponent;
  let fixture: ComponentFixture<RuleModalComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RuleModalComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RuleModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });
});
