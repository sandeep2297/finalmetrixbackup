import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ClientProfileviewComponent } from './client-profileview.component';
import { MaterialModule } from 'src/app/material/material.module';
import { MatToolbarModule } from '@angular/material';

describe('ClientProfileviewComponent', () => {
  let component: ClientProfileviewComponent;
  let fixture: ComponentFixture<ClientProfileviewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ClientProfileviewComponent ],
      imports: [ MaterialModule, MatToolbarModule]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ClientProfileviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

});
