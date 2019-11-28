import { TestBed } from '@angular/core/testing';

import { HttpClientModule } from '@angular/common/http';
import { RouterTestingModule } from '@angular/router/testing';

describe('ClientProfileService', () => {
  beforeEach(() => TestBed.configureTestingModule({
    imports: [HttpClientModule, RouterTestingModule]
  }));

});
