import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { IssuerHomeComponent } from './issuer-home.component';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RouterTestingModule } from '@angular/router/testing';
import { MaterialModule } from 'src/app/material/material.module';
import { MatFormFieldModule } from '@angular/material';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { RuleListItemComponent } from '../profile-home/rule-list-item/rule-list-item.component';


describe('IssuerHomeComponent', () => {
  let component: IssuerHomeComponent;
  let fixture: ComponentFixture<IssuerHomeComponent>;
  // let e1: HTMLElement;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [IssuerHomeComponent, RuleListItemComponent],
      imports: [RouterTestingModule, MaterialModule, MatFormFieldModule,
        ReactiveFormsModule, FormsModule, BrowserAnimationsModule, HttpClientTestingModule]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(IssuerHomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});



