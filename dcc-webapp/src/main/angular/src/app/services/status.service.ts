/**
 * Created by shawn on 19/11/16.
 */
import {Injectable} from "@angular/core";
import {BehaviorSubject, Observable} from "rxjs/Rx";
import "rxjs/add/operator/toPromise";
import {Status} from "../models/status";
import {HttpClient} from "@angular/common/http";
import {StompService} from "@stomp/ng2-stompjs";
import {Message} from '@stomp/stompjs';

@Injectable()
export class StatusService {

    private statusUrl = '/api/interface/status';

    private _status: BehaviorSubject<Status> = new BehaviorSubject(new Status());
    private status: Observable<Status> = this._status.asObservable();

    constructor(private http: HttpClient, private stompService: StompService) {
      this.stompService.subscribe("/status").map((message: Message) => {
        return message.body;
      }).subscribe( (data: string) => {
        this.setStatus(data);
      });
      this.readStatus();
    }

    setStatus(status: string): void {
      let statusObj: Status = new Status();
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
        let status: Status = new Status();
        status.status = "Service Down";
        this._status.next(status);
    }

    getStatus(): Observable<Status> {
        return this.status;
    }
}
