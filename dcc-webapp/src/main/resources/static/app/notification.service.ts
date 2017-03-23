/**
 * Created by shawn on 23/03/17.
 */
import {Injectable} from "@angular/core";
import {Headers, Http} from "@angular/http";
import {BehaviorSubject, Observable} from "rxjs/Rx";
import {Notification} from "./notification";

@Injectable()
export class NotificationService {

    private headers = new Headers({'Content-Type': 'application/json'});
    private notificationsUrl = '/notifications/';

    private _statusUpdates: BehaviorSubject<string> = new BehaviorSubject("");
    private statusUpdates: Observable<string> = this._statusUpdates.asObservable();

    private _messageUpdates: BehaviorSubject<string> = new BehaviorSubject("");
    private messageUpdates: Observable<string> = this._messageUpdates.asObservable();

    private _cabUpdates: BehaviorSubject<number[]> = new BehaviorSubject(null);
    private cabUpdates: Observable<number[]> = this._cabUpdates.asObservable();

    private notificationId: number = -1;

    constructor(private http: Http) {
        this.startFetchingNotifications();
    }

    processNotifications(data: Notification[]): void {
        let status: boolean = false;
        let messages: boolean = false;
        let cabList: number[] = new Array();
        for(let notification of data) {
            if ("STATUS" == notification.type) {
                status = true;
            } else if ("MESSAGES" == notification.type) {
                messages = true;
            } else if ("CAB" == notification.type) {
                cabList.push(Number(notification.value));
            }
            if (notification.notificationId > this.notificationId) {
                this.notificationId = notification.notificationId;
            }
        }
        if (status) {
            this._statusUpdates.next("StatusUpdate");
        }
        if (messages) {
            this._messageUpdates.next("MessageUpdate");
        }
        this._cabUpdates.next(cabList);
    }

    fetchNotifications(): void {
        this.http.get(this.notificationsUrl + this.notificationId)
            .map(response => response.json())
            .subscribe(data => {
            this.processNotifications(data);
        }, error => console.log("Could not fetch notifications."));
    }

    getStatusUpdates(): Observable<string> {
        return this.statusUpdates;
    }

    getMessageUpdates(): Observable<string> {
        return this.messageUpdates;
    }

    getCabUpdates(): Observable<number[]> {
        return this.cabUpdates;
    }

    startFetchingNotifications(): void {
        Observable.interval(1000).subscribe(data => this.fetchNotifications());
    }
}