/**
 * Created by shawn on 12/01/17.
 */
import {Headers, Http} from "@angular/http";
import {Observable, BehaviorSubject} from "rxjs/Rx";
import {Info} from "./info";
import {Injectable} from "@angular/core";

@Injectable()
export class InfoService {

    private headers = new Headers({'Content-Type': 'application/json'});
    private infoUrl = '/info';

    private _info: BehaviorSubject<Info> = new BehaviorSubject(new Info());
    private info: Observable<Info> = this._info.asObservable();

    constructor(private http: Http) {
        this.fetchInfo();
    }

    fetchInfo(): void {
        this.http.get(this.infoUrl).map(response => response.json()).subscribe(data => {
            this.setInfo(data);
        }, error => console.log('Could not load info.'));
    }

    setInfo(data: Info): void {
        this._info.next(data);
    }

    getInfo(): Observable<Info> {
        return this.info;
    }

}