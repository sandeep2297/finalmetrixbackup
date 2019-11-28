import { TestBed } from '@angular/core/testing';
import { PipelineListService } from './pipeline-list.service';
import { HttpClientModule } from '@angular/common/http';

describe('PipelineListService', () => {
  beforeEach(() => TestBed.configureTestingModule({
    imports: [HttpClientModule]
  }));

  it('should be created', () => {
    const service: PipelineListService = TestBed.get(PipelineListService);
    expect(service).toBeTruthy();
  });
});
