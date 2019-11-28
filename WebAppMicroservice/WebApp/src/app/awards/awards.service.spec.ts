import { TestBed } from '@angular/core/testing';

import { AwardsService } from './awards.service';
import { HttpClientTestingModule } from '@angular/common/http/testing';

describe('AwardsService', () => {
  beforeEach(() => TestBed.configureTestingModule({
    imports: [HttpClientTestingModule]
  }));

  it('should be created', () => {
    const service: AwardsService = TestBed.get(AwardsService);
    expect(service).toBeTruthy();
  });
});
