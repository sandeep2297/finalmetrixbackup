import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RulesComponent } from './rules.component';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material';
import { MaterialModule } from 'src/app/material/material.module';
import { RuleService } from '../rule.service';
import { By } from '@angular/platform-browser';
import { RouterTestingModule } from '@angular/router/testing';

describe('RulesComponent', () => {
  let component: RulesComponent;
  let fixture: ComponentFixture<RulesComponent>;
  let e1: HTMLElement;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RulesComponent ],
      imports: [HttpClientTestingModule, BrowserAnimationsModule, ReactiveFormsModule,
      FormsModule, MatFormFieldModule, MaterialModule, RouterTestingModule],
      providers: [RuleService]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RulesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
  it('form invalid when rule name, description, award value and expression value are empty', () => {
    expect(component.ruleForm.valid).toBeFalsy();
  });
  it('until the details are filled do not call the function submitData', async(() => {
    fixture.detectChanges();
    spyOn(component, 'submitRule');
    e1 = fixture.debugElement.query(By.css('button')).nativeElement;
    e1.click();
    expect(component.submitRule).toHaveBeenCalledTimes(0);
  }));
});
