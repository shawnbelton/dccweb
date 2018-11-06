import {TestBed} from '@angular/core/testing';
import {CabService} from './cab.service';

describe('CabService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: CabService = TestBed.get(CabService);
    expect(service).toBeTruthy();
  });
});
