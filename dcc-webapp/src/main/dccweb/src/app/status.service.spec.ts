import {TestBed} from '@angular/core/testing';

import {StatusService} from './status.service';
import {StompService} from '@stomp/ng2-stompjs';
import {HttpClient} from '@angular/common/http';
import {BehaviorSubject} from 'rxjs';

describe('StatusService', () => {
  beforeEach(() => {
    const stompService = jasmine.createSpyObj('StompService', ['subscribe', 'state']);
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
    const behaviour = jasmine.createSpyObj('BehaviorSubject', ['subscribe']);
    http.get.and.returnValue(subscription);
    http.post.and.returnValue(subscription);
    stomp.subscribe.and.returnValue(subscription);
    const service: StatusService = TestBed.get(StatusService);
    expect(service).toBeTruthy();
  });
});
