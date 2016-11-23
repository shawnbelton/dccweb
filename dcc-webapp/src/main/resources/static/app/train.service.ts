/**
 * Created by shawn on 19/11/16.
 */
import {Injectable} from "@angular/core";
import {Headers, Http} from "@angular/http";
import {Observable, BehaviorSubject} from "rxjs/Rx";
import "rxjs/add/operator/toPromise";
import {Train} from "./train";

@Injectable()
export class TrainService {

    private headers = new Headers({'Content-Type': 'application/json'});
    private trainsUrl = '/trains';
    private saveUrl = '/trains/save';

    private _trains: BehaviorSubject<Train[]> = new BehaviorSubject([]);
    private trains: Observable<Train[]> = this._trains.asObservable();

    constructor(private http: Http) {
        this.loadInitialData();
    }

    loadInitialData(): void {
        this.http.get(this.trainsUrl).map(response => response.json()).subscribe(data => {
            this._trains.next(data);
        }, error => console.log('Could not load trains.'));
    }

    saveTrain(train: Train): void {
        this.http.post(this.saveUrl, train).map(response => response.json()).subscribe(data => {
            this._trains.next(data);
        }, error => console.log('Could not load trains.'));
    }

    getTrains(): Observable<Train[]> {
        return this.trains;
    }

}