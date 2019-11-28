import { TestBed } from '@angular/core/testing';

import { PageTitleService } from './page-title.service';
import { HttpClientTestingModule } from '@angular/common/http/testing';

describe('PageTitleService', () => {
  beforeEach(() => TestBed.configureTestingModule({
    imports: [HttpClientTestingModule]
  }));

  it('should be created', () => {
    const service: PageTitleService = TestBed.get(PageTitleService);
    expect(service).toBeTruthy();
  });
});
