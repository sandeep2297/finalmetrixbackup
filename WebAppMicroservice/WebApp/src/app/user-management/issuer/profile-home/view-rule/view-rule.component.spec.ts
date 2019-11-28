import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewRuleComponent } from './view-rule.component';
import { RouterModule } from '@angular/router';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { RuleService } from '../../rule.service';


describe('ViewRuleComponent', () => {
  let component: ViewRuleComponent;
  let fixture: ComponentFixture<ViewRuleComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewRuleComponent ],
      imports: [ RouterModule.forRoot([]), HttpClientTestingModule],
      providers: [RuleService]
   })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewRuleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  // it('should create', () => {
  //   expect(component).toBeTruthy();
  // });
});
