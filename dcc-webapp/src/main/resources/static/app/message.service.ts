/**
 * Created by shawn on 16/11/16.
 */
import {Injectable} from "@angular/core";
import {Headers, Http} from "@angular/http";
import {Observable, BehaviorSubject} from "rxjs/Rx";
import "rxjs/add/operator/toPromise";
import {Message} from "./message";

@Injectable()
export class MessageService {

    private headers = new Headers({'Content-Type': 'application/json'});
    private logEntryUrl = '/messages';

    private _messages: BehaviorSubject<Message[]> = new BehaviorSubject([]);
    private messages: Observable<Message[]> = this._messages.asObservable();

    constructor(private http: Http) {
        this.startFetchingMessages();
    }

    fetchMessages(): void {
        this.http.get(this.logEntryUrl).map(response => response.json()).subscribe(data => {
            this._messages.next(data);
        }, error => console.log('Could not get messages.'));
    }

    getMessages(): Observable<Message[]> {
        return this.messages;
    }

    startFetchingMessages(): void {
        Observable.interval(2000).subscribe(data => this.fetchMessages());
    }

}