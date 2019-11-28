import { TestBed } from '@angular/core/testing';

import { PipelineDetailsService } from './pipeline-details.service';

describe('PipelineDetailsService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: PipelineDetailsService = TestBed.get(PipelineDetailsService);
    expect(service).toBeTruthy();
  });
});
