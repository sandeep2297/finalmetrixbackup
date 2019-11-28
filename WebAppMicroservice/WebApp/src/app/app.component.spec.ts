import { TestBed, async } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { AppComponent } from './app.component';
import { NavbarComponent } from './app-home/navbar/navbar.component';
import { MaterialModule } from './material/material.module';
import { HttpClientModule } from '@angular/common/http';

describe('AppComponent', () => {
  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [
        RouterTestingModule, MaterialModule, HttpClientModule
      ],
      declarations: [
        AppComponent, NavbarComponent
      ],
    }).compileComponents();
  }));


  // it(`should have as title 'Metrix Platform'`, () => {
  //   const fixture = TestBed.createComponent(AppComponent);
  //   const app = fixture.debugElement.componentInstance;
  //   expect(app.title).toEqual('Metrix Platform');
  // });

});
