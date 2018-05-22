/**
 * Created by shawn on 23/03/17.
 */
import {Injectable} from "@angular/core";
import {BehaviorSubject, Observable} from "rxjs/Rx";
import {Notification} from "../models/notification";
import {HttpClient} from "@angular/common/http";

@Injectable()
export class NotificationService {

    private notificationsUrl = '/api/notifications/';

    private _relayUpdates: BehaviorSubject<string> = new BehaviorSubject<string>(null);
    private relayUpdates: Observable<string> = this._relayUpdates.asObservable();

    private _decoderUpdates: BehaviorSubject<string> = new BehaviorSubject<string>(null);
    private decoderUpdates: Observable<string> = this._decoderUpdates.asObservable();

    private notificationId: number = -1;

    constructor(private http: HttpClient) {
        this.startFetchingNotifications();
    }

    processNotifications(data: Notification[]): void {
        let relays: boolean = false;
        let decoders: boolean = false;
        for(let notification of data) {
            if ("RELAY" == notification.type) {
                relays = true;
            } else if ("DECODERS" == notification.type) {
              decoders = true;
            }
            if (notification.notificationId > this.notificationId) {
                this.notificationId = notification.notificationId;
            }
        }
        if (relays) {
            this._relayUpdates.next("RelayUpdates");
        }
        if (decoders) {
          this._decoderUpdates.next("DecodersUpdates");
        }
    }

    fetchNotifications(): void {
        this.http.get(this.notificationsUrl + this.notificationId)
            .subscribe((data: Notification[]) => {
            this.processNotifications(data);
        });
    }

    getRelayUpdates(): Observable<string> {
        return this.relayUpdates;
    }

    getDecoderUpdates(): Observable<string> {
      return this.decoderUpdates;
    }

    startFetchingNotifications(): void {
        Observable.interval(1000).subscribe(data => this.fetchNotifications());
    }
}
