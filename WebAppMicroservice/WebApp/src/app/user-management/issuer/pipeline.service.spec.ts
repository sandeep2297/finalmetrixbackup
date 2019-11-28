import { TestBed } from '@angular/core/testing';

import { PipelineService } from './pipeline.service';
import { HttpClientTestingModule } from '@angular/common/http/testing';

describe('PipelineService', () => {
  beforeEach(() => TestBed.configureTestingModule({
    imports: [HttpClientTestingModule],
    // providers: [PipelineService]
  }));

  it('should be created', () => {
    const service: PipelineService = TestBed.get(PipelineService);
    expect(service).toBeTruthy();
  });
});
