import {TestBed} from '@angular/core/testing';

import {AccessoryDecoderService} from './accessory-decoder.service';

describe('AccessoryDecoderService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: AccessoryDecoderService = TestBed.get(AccessoryDecoderService);
    expect(service).toBeTruthy();
  });
});
