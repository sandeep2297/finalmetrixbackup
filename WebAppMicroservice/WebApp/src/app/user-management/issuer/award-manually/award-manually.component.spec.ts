import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AwardManuallyComponent } from './award-manually.component';
import { ReactiveFormsModule } from '@angular/forms';
import { MaterialModule } from 'src/app/material/material.module';
import { RouterTestingModule } from '@angular/router/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

describe('AwardManuallyComponent', () => {
  let component: AwardManuallyComponent;
  let fixture: ComponentFixture<AwardManuallyComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [AwardManuallyComponent],
      imports: [ReactiveFormsModule, MaterialModule, RouterTestingModule, HttpClientTestingModule, BrowserAnimationsModule]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AwardManuallyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
