/**
 * Created by shawn on 07/06/17.
 */
import {Injectable} from "@angular/core";
import {Headers, Http} from "@angular/http";
import {NotificationService} from "./notification.service";
import {BehaviorSubject, Observable} from "rxjs/Rx";
import {RelayController} from "../models/relayController";

@Injectable()
export class RelayService {

    private headers = new Headers({'Content-Type': 'application/json'});
    private allRelayControllersUrl = '/api/relay-controller/all';
    private saveUrl = '/api/relay-controller/save';
    private updateUrl = '/api/relay-controller/update-value';

    private _relayControllers: BehaviorSubject<RelayController[]> = new BehaviorSubject(null);
    private relayControllers: Observable<RelayController[]> = this._relayControllers.asObservable();

    constructor(private http: Http, private notificationService: NotificationService) {
        this.fetchRelayControllers();
        this.notificationService.getRelayUpdates().subscribe(data => this.fetchRelayControllers());
    }

    fetchRelayControllers(): void {
        this.http.get(this.allRelayControllersUrl).map(response => response.json()).subscribe(data => {
            this._relayControllers.next(data);
        }, error => console.log('Could not load relay controllers.'));
    }

    saveRelayController(relayController: RelayController): void {
        this.http.post(this.saveUrl, relayController).map(response => response.json()).subscribe(data => {
            this._relayControllers.next(data);
        }, error => console.log('Could not load relay controllers.'));
    }

    updateRelayValue(relayController: RelayController): void {
        this.http.post(this.updateUrl, relayController).map(response => response.json()).subscribe(data => {
            this._relayControllers.next(data);
        }, error => console.log('Could not load relay controllers.'));
    }

    getRelayControllers(): Observable<RelayController[]> {
        return this.relayControllers;
    }
}