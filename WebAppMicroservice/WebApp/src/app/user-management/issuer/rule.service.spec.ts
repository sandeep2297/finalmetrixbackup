import { TestBed } from '@angular/core/testing';

import { RuleService } from './rule.service';
import { HttpClientTestingModule } from '@angular/common/http/testing';

describe('RuleService', () => {
  beforeEach(() => TestBed.configureTestingModule({
    imports: [HttpClientTestingModule]
  }));

  it('should be created', () => {
    const service: RuleService = TestBed.get(RuleService);
    expect(service).toBeTruthy();
  });
});
