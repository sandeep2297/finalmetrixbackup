import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdmindashboardComponent } from './admindashboard.component';
import { MatIconModule } from '@angular/material';
import { MaterialModule } from 'src/app/material/material.module';
import { UserManagementModule } from '../user-management.module';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

describe('AdmindashboardComponent', () => {
  let component: AdmindashboardComponent;
  let fixture: ComponentFixture<AdmindashboardComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdmindashboardComponent ],
      imports: [ MatIconModule, MaterialModule, HttpClientTestingModule, BrowserAnimationsModule]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdmindashboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  // it('should create', () => {
  //   expect(component).toBeTruthy();
  // });
});
