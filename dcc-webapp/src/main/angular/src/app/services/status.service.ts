/**
 * Created by shawn on 19/11/16.
 */
import {Injectable} from "@angular/core";
import {Headers, Http} from "@angular/http";
import {BehaviorSubject, Observable} from "rxjs/Rx";
import "rxjs/add/operator/toPromise";
import {Status} from "../models/status";
import {NotificationService} from "./notification.service";

@Injectable()
export class StatusService {

    private headers = new Headers({'Content-Type': 'application/json'});
    private statusUrl = '/api/interface/status';

    private _status: BehaviorSubject<Status> = new BehaviorSubject(new Status());
    private status: Observable<Status> = this._status.asObservable();

    constructor(private http: Http, private notificationService: NotificationService) {
        this.notificationService.getStatusUpdates().subscribe(data => this.readStatus());
    }

    readStatus(): void {
        this.http.get(this.statusUrl).map(response => response.json())
            .subscribe(data => {
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