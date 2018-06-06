/**
 * Created by shawn on 12/01/17.
 */
import {BehaviorSubject, Observable} from "rxjs/Rx";
import {Info} from "../models/info";
import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";

@Injectable()
export class InfoService {

    private infoUrl = '/info';

    private _info: BehaviorSubject<Info> = new BehaviorSubject(new Info());
    private info: Observable<Info> = this._info.asObservable();

    constructor(private http: HttpClient) {
        this.fetchInfo();
    }

    fetchInfo(): void {
        this.http.get(this.infoUrl).subscribe((data: Info) => {
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
