import { async, ComponentFixture, TestBed, inject } from '@angular/core/testing';

import { LoginDialogBoxComponent } from './login-dialog-box.component';
import { OverlayContainer } from '@angular/cdk/overlay';

import { MatDialog, MatDialogModule, MatDialogRef } from '@angular/material';
import { MaterialModule } from 'src/app/material/material.module';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RouterTestingModule } from '@angular/router/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';

describe('LoginDialogBoxComponent', () => {
  let dialog: MatDialog;
  let overlayContainer: OverlayContainer;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LoginDialogBoxComponent ],
      imports: [
        MatDialogModule,  MaterialModule, BrowserAnimationsModule, RouterTestingModule, HttpClientTestingModule
      ],
      providers: [ { provide: MatDialogRef} ]
    });

    TestBed.overrideModule(BrowserAnimationsModule, {
      set: {
        entryComponents: [ LoginDialogBoxComponent ]
      }
    });

    TestBed.compileComponents();
  }));

  beforeEach(inject([MatDialog, OverlayContainer],
    (d: MatDialog, oc: OverlayContainer) => {
      dialog = d;
      overlayContainer = oc;
    })
  );

  afterEach(() => {
    overlayContainer.ngOnDestroy();
  });

  it('should open a dialog ', () => {
    const dialogRef = dialog.open( LoginDialogBoxComponent, {
      width: '400px', height: '250px'
    });
    expect(dialogRef.componentInstance instanceof LoginDialogBoxComponent).toBe(true);
  });
});
