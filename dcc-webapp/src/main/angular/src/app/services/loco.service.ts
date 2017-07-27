/**
 * Created by shawn on 19/11/16.
 */
import {Injectable} from "@angular/core";
import {Headers, Http} from "@angular/http";
import {BehaviorSubject, Observable} from "rxjs/Rx";
import "rxjs/add/operator/toPromise";
import {Loco} from "../models/loco";

@Injectable()
export class LocoService {

    private headers = new Headers({'Content-Type': 'application/json'});
    private locosUrl = '/api/locos/all';
    private saveUrl = '/api/locos/save';

    private _locos: BehaviorSubject<Loco[]> = new BehaviorSubject([]);
    private locos: Observable<Loco[]> = this._locos.asObservable();

    constructor(private http: Http) {
        this.loadInitialData();
    }

    loadInitialData(): void {
        this.http.get(this.locosUrl).map(response => response.json()).subscribe(data => {
            this._locos.next(data);
        }, error => console.log('Could not load locos.'));
    }

    saveLoco(loco: Loco): void {
        this.http.post(this.saveUrl, loco).map(response => response.json()).subscribe(data => {
            this._locos.next(data);
        }, error => console.log('Could not load locos.'));
    }

    getLocos(): Observable<Loco[]> {
        return this.locos;
    }

}
