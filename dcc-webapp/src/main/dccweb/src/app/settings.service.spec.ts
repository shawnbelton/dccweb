import {TestBed} from '@angular/core/testing';

import {SettingsService} from './settings.service';
import {HttpClient} from '@angular/common/http';

describe('SettingsService', () => {
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
    const service: SettingsService = TestBed.get(SettingsService);
    expect(service).toBeTruthy();
  });
});
