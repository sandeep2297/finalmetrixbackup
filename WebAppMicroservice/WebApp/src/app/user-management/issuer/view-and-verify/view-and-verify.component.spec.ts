import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewAndVerifyComponent } from './view-and-verify.component';
import { MaterialModule } from 'src/app/material/material.module';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';

describe('ViewAndVerifyComponent', () => {
  let component: ViewAndVerifyComponent;
  let fixture: ComponentFixture<ViewAndVerifyComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewAndVerifyComponent ],
      imports: [ MaterialModule ],
      providers: [ { provide: MatDialogRef },
        { provide: MAT_DIALOG_DATA }
       ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewAndVerifyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  // it('should create', () => {
  //   expect(component).toBeTruthy();
  // });
});
