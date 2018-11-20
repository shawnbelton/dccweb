import {TestBed} from '@angular/core/testing';

import {MacroService} from './macro.service';
import {HttpClient} from '@angular/common/http';

describe('MacroService', () => {
  beforeEach(() => {
    const httpClient = jasmine.createSpyObj('HttpClient', ['get', 'post']);
    TestBed.configureTestingModule({
      providers: [
        {provide: HttpClient, useValue: httpClient}
      ]
    });
  });

  it('should be created', () => {
    const subscription = jasmine.createSpyObj('Observable', ['subscribe']);
    const http: jasmine.SpyObj<HttpClient> = TestBed.get(HttpClient);
    http.get.and.returnValue(subscription);
    http.post.and.returnValue(subscription);
    const service: MacroService = TestBed.get(MacroService);
    expect(service).toBeTruthy();
  });
});
