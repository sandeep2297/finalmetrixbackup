import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { IssuerlistComponent } from './issuerlist.component';

describe('IssuerlistComponent', () => {
  let component: IssuerlistComponent;
  let fixture: ComponentFixture<IssuerlistComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ IssuerlistComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(IssuerlistComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  // it('should create', () => {
  //   expect(component).toBeTruthy();
  // });
});
