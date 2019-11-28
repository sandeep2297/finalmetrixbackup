import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ConfigRuleHelpComponent } from './config-rule-help.component';

describe('ConfigRuleHelpComponent', () => {
  let component: ConfigRuleHelpComponent;
  let fixture: ComponentFixture<ConfigRuleHelpComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ConfigRuleHelpComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ConfigRuleHelpComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });
});
