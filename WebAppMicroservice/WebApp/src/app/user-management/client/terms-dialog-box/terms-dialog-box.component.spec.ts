import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TermsDialogBoxComponent } from './terms-dialog-box.component';
import { MaterialModule } from 'src/app/material/material.module';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { MatDialogRef } from '@angular/material';

describe('TermsDialogBoxComponent', () => {
  let component: TermsDialogBoxComponent;
  let fixture: ComponentFixture<TermsDialogBoxComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TermsDialogBoxComponent ],
      imports: [ MaterialModule, HttpClientTestingModule ],
      providers: [ { provide: MatDialogRef } ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TermsDialogBoxComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
