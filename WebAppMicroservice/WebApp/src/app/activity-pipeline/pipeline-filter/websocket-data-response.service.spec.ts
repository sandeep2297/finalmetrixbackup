import { TestBed } from '@angular/core/testing';

import { WebsocketDataResponseService } from './websocket-data-response.service';

describe('WebsocketDataResponseService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: WebsocketDataResponseService = TestBed.get(WebsocketDataResponseService);
    expect(service).toBeTruthy();
  });
});
