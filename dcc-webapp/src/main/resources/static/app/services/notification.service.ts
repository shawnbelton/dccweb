/**
 * Created by shawn on 23/03/17.
 */
import {Injectable} from "@angular/core";
import {Headers, Http} from "@angular/http";
import {BehaviorSubject, Observable} from "rxjs/Rx";
import {Notification} from "../models/notification";

@Injectable()
export class NotificationService {

    private headers = new Headers({'Content-Type': 'application/json'});
    private notificationsUrl = '/api/notifications/';

    private _statusUpdates: BehaviorSubject<string> = new BehaviorSubject(null);
    private statusUpdates: Observable<string> = this._statusUpdates.asObservable();

    private _messageUpdates: BehaviorSubject<string> = new BehaviorSubject(null);
    private messageUpdates: Observable<string> = this._messageUpdates.asObservable();

    private _blockUpdates: BehaviorSubject<string> = new BehaviorSubject(null);
    private blockUpdates: Observable<string> = this._blockUpdates.asObservable();

    private _cabUpdates: BehaviorSubject<number[]> = new BehaviorSubject(null);
    private cabUpdates: Observable<number[]> = this._cabUpdates.asObservable();

    private _accessoryUpdates: BehaviorSubject<string> = new BehaviorSubject(null);
    private accessoryUpdates: Observable<string> = this._accessoryUpdates.asObservable();

    private notificationId: number = -1;

    constructor(private http: Http) {
        this.startFetchingNotifications();
    }

    processNotifications(data: Notification[]): void {
        let status: boolean = false;
        let messages: boolean = false;
        let accessories: boolean = false;
        let blocks: boolean = false;
        let cabList: number[] = new Array();
        for(let notification of data) {
            if ("STATUS" == notification.type) {
                status = true;
            } else if ("MESSAGES" == notification.type) {
                messages = true;
            } else if ("ACCESSORY" == notification.type) {
                accessories = true;
            } else if ("CAB" == notification.type) {
                cabList.push(Number(notification.value));
            } else if ("BLOCK" == notification.type) {
                blocks = true;
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
        if (accessories) {
            this._accessoryUpdates.next("AccessoryUpdate");
        }
        if (blocks) {
            this._blockUpdates.next("BlockUpdate");
        }
        if (cabList.length > 0) {
            this._cabUpdates.next(cabList);
        }
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

    getBlockUpdates(): Observable<string> {
        return this.blockUpdates;
    }

    getCabUpdates(): Observable<number[]> {
        return this.cabUpdates;
    }

    getAccessoryUpdates(): Observable<string> {
        return this.accessoryUpdates;
    }

    startFetchingNotifications(): void {
        Observable.interval(1000).subscribe(data => this.fetchNotifications());
    }
}