/**
 * Created by shawn on 16/11/16.
 */
import {Injectable} from "@angular/core";
import {Headers, Http} from "@angular/http";
import {BehaviorSubject, Observable} from "rxjs/Rx";
import {Message} from "../models/message";
import {NotificationService} from "./notification.service";
import {StompService} from "./stomp.service";

@Injectable()
export class MessageService {

    private headers = new Headers({'Content-Type': 'application/json'});
    private logEntryUrl = '/api/messages';

    private _messages: BehaviorSubject<Message[]> = new BehaviorSubject([]);
    private messages: Observable<Message[]> = this._messages.asObservable();

    constructor(private http: Http, private stompService: StompService) {
        this.stompService.subscribe('/logging', (data: Message) => {
          this.updateMessages(data);
        });
    }

    updateMessages(message: Message): void {
      let currentMessages: Message[] = this._messages.value;
      let newMessages: Message[] = [];
      newMessages.push(message);
      for(let itrMessage of currentMessages) {
        if (newMessages.length < 6) {
          newMessages.push(itrMessage);
        }
      }
      this._messages.next(newMessages);
    }

    fetchMessages(): void {
        this.http.get(this.logEntryUrl).map(response => response.json()).subscribe(data => {
            this._messages.next(data);
        }, error => console.log('Could not get messages.'));
    }

    getMessages(): Observable<Message[]> {
        return this.messages;
    }

}
