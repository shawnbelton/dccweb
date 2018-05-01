import {Injectable} from "@angular/core";
import Stomp from 'stompjs';
import SockJS from 'sockjs-client';
import {Subscription} from "../models/subscription";

@Injectable()
export class StompService {
  private serverUrl = '/socket';
  private stompClient;
  private initialized = true;
  private connected = false;

  private subscriptions: Subscription[] = [];

  constructor() {
    this.initializeWebSocketConnection();
  }

  initializeWebSocketConnection(): void {
    let ws = new SockJS(this.serverUrl);
    this.stompClient = Stomp.over(ws);
    let that = this;
    this.stompClient.connect({}, function(frame) {that.connect()});
    this.initialized = false;
  }

  connect(): void {
    for(let subscription of this.subscriptions) {
      this.doSubscription(subscription);
    }
    this.connected = true;
  }

  private doSubscription(subscription) {
    this.stompClient.subscribe(subscription.destination, function (response) {
      let message = JSON.parse(response.body);
      let headers = response.headers;
      subscription.callback(message, headers);
    }, subscription.headers);
  }

  public subscribe(destination:string, callback:any, headers?:Object): void {
    let subscription: Subscription = new Subscription();
    subscription.destination = destination;
    subscription.callback = callback;
    subscription.headers = headers || {};
    this.subscriptions.push(subscription);
    if (this.connected) {
      this.doSubscription(subscription);
    }
  }
}
