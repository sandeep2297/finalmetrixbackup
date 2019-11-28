import { TestBed } from '@angular/core/testing';

import { IssueAwardsService } from './issue-awards.service';
import { HttpClientTestingModule } from '@angular/common/http/testing';

describe('IssueAwardsService', () => {
  beforeEach(() => TestBed.configureTestingModule({
    imports: [HttpClientTestingModule],
    providers: [IssueAwardsService]
  }));

  it('should be created', () => {
    const service: IssueAwardsService = TestBed.get(IssueAwardsService);
    expect(service).toBeTruthy();
  });
});
