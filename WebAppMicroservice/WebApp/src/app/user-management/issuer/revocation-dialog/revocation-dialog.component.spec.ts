import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RevocationDialogComponent } from './revocation-dialog.component';

describe('RevocationDialogComponent', () => {
  let component: RevocationDialogComponent;
  let fixture: ComponentFixture<RevocationDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RevocationDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RevocationDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

});
