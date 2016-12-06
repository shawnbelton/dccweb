/**
 * Created by shawn on 19/11/16.
 */
import {Injectable} from "@angular/core";
import {Headers, Http} from "@angular/http";
import {Observable, BehaviorSubject} from "rxjs/Rx";
import "rxjs/add/operator/toPromise";
import {Status} from "./status";

@Injectable()
export class StatusService {

    private headers = new Headers({'Content-Type': 'application/json'});
    private statusUrl = '/interface/status';

    private _status: BehaviorSubject<Status> = new BehaviorSubject(new Status());
    private status: Observable<Status> = this._status.asObservable();

    constructor(private http: Http) {
        this.startFetchingStatus();
    }

    readStatus(): void {
        this.http.get(this.statusUrl).map(response => response.json())
            .subscribe(data => {
                this._status.next(data);
            }, error => console.log('Could not load status.'));
    }

    startFetchingStatus(): void {
        Observable.interval(2000).subscribe(data => {
            this.readStatus();
        });
    }

    getStatus(): Observable<Status> {
        return this.status;
    }
}