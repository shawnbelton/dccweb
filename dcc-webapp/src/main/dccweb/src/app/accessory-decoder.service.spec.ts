import {TestBed} from '@angular/core/testing';

import {AccessoryDecoderService} from './accessory-decoder.service';
import {HttpClient} from '@angular/common/http';
import {StompService} from '@stomp/ng2-stompjs';

describe('AccessoryDecoderService', () => {

  beforeEach(() => {
    const stompService = jasmine.createSpyObj('StompService', ['subscribe']);
    const httpClient = jasmine.createSpyObj('HttpClient', ['get', 'post']);
    TestBed.configureTestingModule({
      providers: [
        {provide: HttpClient, useValue: httpClient},
        {provide: StompService, useValue: stompService}
      ]
    });
  });

  it('should be created', () => {
    const subscription = jasmine.createSpyObj('Observable', ['subscribe']);
    const stomp: jasmine.SpyObj<StompService> = TestBed.get(StompService);
    const http: jasmine.SpyObj<HttpClient> = TestBed.get(HttpClient);
    http.get.and.returnValue(subscription);
    http.post.and.returnValue(subscription);
    stomp.subscribe.and.returnValue(subscription);
    const service: AccessoryDecoderService = TestBed.get(AccessoryDecoderService);
    expect(service).toBeTruthy();
  });
});
