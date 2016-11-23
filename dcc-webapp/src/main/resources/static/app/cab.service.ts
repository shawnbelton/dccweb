/**
 * Created by shawn on 23/11/16.
 */
import {Injectable} from "@angular/core";
import {Headers, Http} from "@angular/http";
import {Cab} from "./cab";
import {BehaviorSubject, Observable} from "rxjs/Rx";
import {Train} from "./train";

@Injectable()
export class CabService {

    private headers = new Headers({'Content-Type': 'application/json'});
    private fetchCabUrl = '/trains/cab';
    private updateCabUrl = '/trains/cab/update';
    private updateCabFunctionUrl = '/trains/cab/updateFunction';

    private _cab: BehaviorSubject<Cab> = new BehaviorSubject(null);
    private cab: Observable<Cab> = this._cab.asObservable();
    private response: boolean;

    constructor(private http: Http) {}

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