import {TestBed} from '@angular/core/testing';

import {InfoService} from './info.service';
import {HttpClient} from '@angular/common/http';

describe('InfoService', () => {
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
    const service: InfoService = TestBed.get(InfoService);
    expect(service).toBeTruthy();
  });
});
