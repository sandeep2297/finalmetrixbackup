import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ClientDashboardComponent } from './client-dashboard.component';

import { MaterialModule } from 'src/app/material/material.module';


import { RouterTestingModule } from '@angular/router/testing';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

describe('ClientDashboardComponent', () => {
  let component: ClientDashboardComponent;
  let fixture: ComponentFixture<ClientDashboardComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ClientDashboardComponent],
      imports: [RouterTestingModule, MaterialModule, HttpClientModule, FormsModule, ReactiveFormsModule]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ClientDashboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

});
