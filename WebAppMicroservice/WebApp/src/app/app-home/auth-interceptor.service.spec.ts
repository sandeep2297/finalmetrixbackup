import { TestBed } from '@angular/core/testing';

import { AuthInterceptorService } from './auth-interceptor.service';
import { CookieService } from 'ngx-cookie-service';
import { HttpClientModule } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';

describe('AuthInterceptorService', () => {
  beforeEach(() => TestBed.configureTestingModule({
    imports: [  HttpClientTestingModule ],
    providers: [ CookieService ]
  }));

  // it('should be created', () => {
  //   const service: AuthInterceptorService = TestBed.get(AuthInterceptorService);
  //   expect(service).toBeTruthy();
  // });
});
