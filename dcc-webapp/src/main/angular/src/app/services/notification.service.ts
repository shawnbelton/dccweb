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

    private _blockUpdates: BehaviorSubject<string> = new BehaviorSubject<string>(null);
    private blockUpdates: Observable<string> = this._blockUpdates.asObservable();

    private _relayUpdates: BehaviorSubject<string> = new BehaviorSubject<string>(null);
    private relayUpdates: Observable<string> = this._relayUpdates.asObservable();

    private _cabUpdates: BehaviorSubject<number[]> = new BehaviorSubject<number[]>(null);
    private cabUpdates: Observable<number[]> = this._cabUpdates.asObservable();

    private _accessoryUpdates: BehaviorSubject<string> = new BehaviorSubject<string>(null);
    private accessoryUpdates: Observable<string> = this._accessoryUpdates.asObservable();

    private _decoderUpdates: BehaviorSubject<string> = new BehaviorSubject<string>(null);
    private decoderUpdates: Observable<string> = this._decoderUpdates.asObservable();

    private notificationId: number = -1;

    constructor(private http: Http) {
        this.startFetchingNotifications();
    }

    processNotifications(data: Notification[]): void {
        let accessories: boolean = false;
        let blocks: boolean = false;
        let relays: boolean = false;
        let decoders: boolean = false;
        let cabList: number[] = new Array();
        for(let notification of data) {
            if ("ACCESSORY" == notification.type) {
                accessories = true;
            } else if ("CAB" == notification.type) {
                cabList.push(Number(notification.value));
            } else if ("BLOCK" == notification.type) {
                blocks = true;
            } else if ("RELAY" == notification.type) {
                relays = true;
            } else if ("DECODERS" == notification.type) {
              decoders = true;
            }
            if (notification.notificationId > this.notificationId) {
                this.notificationId = notification.notificationId;
            }
        }
        if (accessories) {
            this._accessoryUpdates.next("AccessoryUpdate");
        }
        if (blocks) {
            this._blockUpdates.next("BlockUpdate");
        }
        if (relays) {
            this._relayUpdates.next("RelayUpdates");
        }
        if (cabList.length > 0) {
            this._cabUpdates.next(cabList);
        }
        if (decoders) {
          this._decoderUpdates.next("DecodersUpdates");
        }
    }

    fetchNotifications(): void {
        this.http.get(this.notificationsUrl + this.notificationId)
            .map(response => response.json())
            .subscribe(data => {
            this.processNotifications(data);
        });
    }

    getBlockUpdates(): Observable<string> {
        return this.blockUpdates;
    }

    getRelayUpdates(): Observable<string> {
        return this.relayUpdates;
    }

    getCabUpdates(): Observable<number[]> {
        return this.cabUpdates;
    }

    getAccessoryUpdates(): Observable<string> {
        return this.accessoryUpdates;
    }

    getDecoderUpdates(): Observable<string> {
      return this.decoderUpdates;
    }

    startFetchingNotifications(): void {
        Observable.interval(1000).subscribe(data => this.fetchNotifications());
    }
}
