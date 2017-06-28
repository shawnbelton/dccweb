/**
 * Created by shawn on 23/11/16.
 */
import {Injectable} from "@angular/core";
import {Headers, Http} from "@angular/http";
import {Cab} from "../models/cab";
import {BehaviorSubject, Observable} from "rxjs/Rx";
import {Train} from "../models/train";
import {NotificationService} from "./notification.service";

@Injectable()
export class CabService {

    private headers = new Headers({'Content-Type': 'application/json'});
    private fetchCabUrl = '/api/trains/cab';
    private updateCabUrl = '/api/trains/cab/update';
    private updateCabFunctionUrl = '/api/trains/cab/updateFunction';

    private _cab: BehaviorSubject<Cab> = new BehaviorSubject(null);
    private cab: Observable<Cab> = this._cab.asObservable();
    private response: boolean;

    constructor(private http: Http, private notificationService: NotificationService) {
        this.notificationService.getCabUpdates().subscribe(data => this.checkUpdates(data));
    }

    checkUpdates(cabList: number[]): void {
        let inList: boolean = false;
        let current: Cab = this._cab.getValue();
        if (null != current) {
            for(let trainId of cabList) {
                if (trainId == current.train.trainId) {
                    inList = true;
                }
            }
            if (inList) {
                this.setTrain(current.train);
            }
        }
    }

    updateCab(cab: Cab): void {
        this.http.post(this.updateCabUrl, cab).map(response => response.json()).subscribe(data => this.response = data);
    }

    updateCabFunction(cab: Cab): void {
        this.http.post(this.updateCabFunctionUrl, cab).map(response => response.json()).subscribe(data => this.response = data);
    }

    setTrain(train: Train): void {
        this.http.post(this.fetchCabUrl, train).map(response => response.json()).subscribe(data => {
            this._cab.next(data);
        }, error => console.log('Could not load cab.'));
    }

    getCab(): Observable<Cab> {
        return this.cab;
    }
}