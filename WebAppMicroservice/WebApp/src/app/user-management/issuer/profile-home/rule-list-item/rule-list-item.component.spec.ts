import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RuleListItemComponent } from './rule-list-item.component';
import { MatIconModule } from '@angular/material';
import { RouterTestingModule } from '@angular/router/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { RuleService } from '../../rule.service';

describe('RuleListItemComponent', () => {
  let component: RuleListItemComponent;
  let fixture: ComponentFixture<RuleListItemComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RuleListItemComponent ],
      imports: [MatIconModule, RouterTestingModule, HttpClientTestingModule],
      providers: [RuleService]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RuleListItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

    // it('should create', () => {
    //   expect(component).toBeTruthy();
    // });
});
