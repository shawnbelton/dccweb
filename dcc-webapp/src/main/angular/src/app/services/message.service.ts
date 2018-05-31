/**
 * Created by shawn on 16/11/16.
 */
import {Injectable} from "@angular/core";
import {BehaviorSubject, Observable} from "rxjs/Rx";
import {LogMessage} from "../models/log.message";
import {HttpClient} from "@angular/common/http";
import {StompService} from "@stomp/ng2-stompjs";
import {Message} from '@stomp/stompjs';

@Injectable()
export class MessageService {

    private logEntryUrl = '/api/messages';

    private _messages: BehaviorSubject<LogMessage[]> = new BehaviorSubject([]);
    private messages: Observable<LogMessage[]> = this._messages.asObservable();

    constructor(private http: HttpClient, private stompService: StompService) {
      this.stompService.subscribe('/logging').map((message: Message) => {
        return message.body;
      }).subscribe((data: string) => {
          this.updateMessages(JSON.parse(data));
      });
    }

    updateMessages(message: LogMessage): void {
      let currentMessages: LogMessage[] = this._messages.value;
      let newMessages: LogMessage[] = [];
      newMessages.push(message);
      for(let itrMessage of currentMessages) {
        if (newMessages.length < 6) {
          newMessages.push(itrMessage);
        }
      }
      this._messages.next(newMessages);
    }

    fetchMessages(): void {
        this.http.get(this.logEntryUrl).subscribe((data: LogMessage[]) => {
            this._messages.next(data);
        }, error => console.log('Could not get messages.'));
    }

    getMessages(): Observable<LogMessage[]> {
        return this.messages;
    }

}
