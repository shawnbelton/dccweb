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

    constructor(private http: Http, private notificationService: NotificationService, private stompService: StompService) {
        this.notificationService.getMessageUpdates().subscribe(data => this.fetchMessages());
        this.stompService.subscribe('/logging', (data) => {
          console.log(data);
        });
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
