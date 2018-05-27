/**
 * Created by shawn on 23/03/17.
 */
import {Injectable} from "@angular/core";
import {Observable} from "rxjs/Rx";
import {HttpClient} from "@angular/common/http";

@Injectable()
export class NotificationService {

    private notificationId: number = -1;

    constructor(private http: HttpClient) {
        this.startFetchingNotifications();
    }

    fetchNotifications(): void {
    }

    startFetchingNotifications(): void {
        Observable.interval(1000).subscribe(data => this.fetchNotifications());
    }
}
