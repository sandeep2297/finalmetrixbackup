import { TestBed } from '@angular/core/testing';
import { PipelineFilterService } from './pipeline-filter.service';
import { HttpClientModule } from '@angular/common/http';

describe('PipelineFilterService', () => {
  beforeEach(() => TestBed.configureTestingModule({
    imports: [HttpClientModule]
  }));

  it('should be created', () => {
    const service: PipelineFilterService = TestBed.get(PipelineFilterService);
    expect(service).toBeTruthy();
  });
});
