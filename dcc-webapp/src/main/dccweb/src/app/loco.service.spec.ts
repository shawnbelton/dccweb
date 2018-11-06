import {TestBed} from '@angular/core/testing';

import {LocoService} from './loco.service';

describe('LocoService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: LocoService = TestBed.get(LocoService);
    expect(service).toBeTruthy();
  });
});
