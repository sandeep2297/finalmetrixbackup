import { async, ComponentFixture, TestBed, inject } from '@angular/core/testing';

import { ViewIssuerBadgesComponent } from './view-issuer-badges.component';
import { MaterialModule } from 'src/app/material/material.module';
import { MatDialogRef, MatDialogModule, MatDialog, MAT_DIALOG_DATA } from '@angular/material';
import { OverlayContainer } from '@angular/cdk/overlay';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClientTestingModule } from '@angular/common/http/testing';

describe('ViewIssuerBadgesComponent', () => {
  // let dialog: MatDialog;
  // let overlayContainer: OverlayContainer;
  let component: ViewIssuerBadgesComponent;
  let fixture: ComponentFixture<ViewIssuerBadgesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ViewIssuerBadgesComponent],
      imports: [MaterialModule, MatDialogModule, HttpClientTestingModule, BrowserAnimationsModule],
      providers: [{ provide: MatDialogRef },
      { provide: MAT_DIALOG_DATA }]
    });
    TestBed.compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewIssuerBadgesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
  TestBed.overrideModule(BrowserAnimationsModule, {
    set: {
      entryComponents: [ViewIssuerBadgesComponent]
    }
  });

  // beforeEach(inject([MatDialog, OverlayContainer],
  //   (d: MatDialog, oc: OverlayContainer) => {
  //     dialog = d;
  //     overlayContainer = oc;
  //   })
  // );

  // afterEach(() => {
  //   overlayContainer.ngOnDestroy();
  // });

  // it('should open a dialog ', () => {
  //   const dialogRef = dialog.open(ViewIssuerBadgesComponent, {
  //     width: '400px', height: '250px'
  //   });
  //   expect(dialogRef.componentInstance instanceof ViewIssuerBadgesComponent).toBe(true);
  // });
});
