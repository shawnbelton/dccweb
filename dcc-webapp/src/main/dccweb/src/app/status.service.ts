import {Injectable} from '@angular/core';
import {BehaviorSubject, Observable} from 'rxjs';
import {Status} from './models/status';
import {HttpClient} from '@angular/common/http';
import {StompService, StompState} from '@stomp/ng2-stompjs';
import {Message} from '@stomp/stompjs';

@Injectable({
  providedIn: 'root'
})
export class StatusService {

  private statusUrl = '/api/interface/status';

  private _status: BehaviorSubject<Status> = new BehaviorSubject(new Status());
  private status: Observable<Status> = this._status.asObservable();

  constructor(private http: HttpClient, private stompService: StompService) {
    this.stompService.subscribe('/status').subscribe(this.on_next);
    this.stompService.state.subscribe((data: StompState) => this.updateStompStatus(data));
    this.readStatus();
  }

  public on_next = (message: Message) => {
    this.setStatus(JSON.parse(message.body));
  }

  updateStompStatus(state: StompState): void {
    switch (state) {
      case 0:
        this._status.next(this.disconnected());
        break;
      case 2:
        this.readStatus();
        break;
    }
  }

  disconnected(): Status {
    const status: Status = new Status();
    status.status = 'DISCONNECTED';
    return status;
  }

  setStatus(status: string): void {
    const statusObj: Status = new Status();
    statusObj.status = status;
    this._status.next(statusObj);
  }

  readStatus(): void {
    this.http.get(this.statusUrl)
      .subscribe((data: Status) => {
        this._status.next(data);
      }, error => this.unableToReadStatus());
  }

  unableToReadStatus(): void {
    const status: Status = new Status();
    status.status = 'Service Down';
    this._status.next(status);
  }

  getStatus(): Observable<Status> {
    return this.status;
  }
}
