import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RevocationDialogBoxComponent } from './revocation-dialog-box.component';

describe('RevocationDialogBoxComponent', () => {
  let component: RevocationDialogBoxComponent;
  let fixture: ComponentFixture<RevocationDialogBoxComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RevocationDialogBoxComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RevocationDialogBoxComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  // it('should create', () => {
  //   expect(component).toBeTruthy();
  // });
});
