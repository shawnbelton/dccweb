import {Injectable} from '@angular/core';
import {BehaviorSubject, Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import {StompService} from '@stomp/ng2-stompjs';
import {Message} from '@stomp/stompjs';
import {LogMessage} from './models/log-message';

@Injectable({
  providedIn: 'root'
})
export class MessageService {

  private logEntryUrl = '/api/messages';

  private _messages: BehaviorSubject<LogMessage[]> = new BehaviorSubject([]);
  private messages: Observable<LogMessage[]> = this._messages.asObservable();

  constructor(private http: HttpClient, private stompService: StompService) {
    this.stompService.subscribe('/logging').subscribe(this.on_next);
  }

  public on_next = (message: Message) => {
    this.updateMessages(JSON.parse(message.body));
  }

  updateMessages(message: LogMessage): void {
    const currentMessages: LogMessage[] = this._messages.value;
    const newMessages: LogMessage[] = [];
    newMessages.push(message);
    for (const itrMessage of currentMessages) {
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
