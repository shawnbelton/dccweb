import {TestBed} from '@angular/core/testing';

import {DecoderService} from './decoder.service';

describe('DecoderService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: DecoderService = TestBed.get(DecoderService);
    expect(service).toBeTruthy();
  });
});
