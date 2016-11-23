/**
 * Created by shawn on 16/11/16.
 */
import {Injectable} from "@angular/core";
import {Headers, Http} from "@angular/http";
import {Observable} from "rxjs/Rx";
import "rxjs/add/operator/toPromise";
import {Message} from "./message";

@Injectable()
export class MessageService {

    private headers = new Headers({'Content-Type': 'application/json'});
    private logEntryUrl = '/messages';

    constructor(private http: Http) {}

    getMessagePromise(): Promise<Message[]> {
        return this.http.get(this.logEntryUrl)
            .toPromise()
            .then(response => response.json() as Message[])
            .catch(this.handleError);
    }

    getMessages(): Observable<Message[]> {
        //return Observable.fromPromise(this.getMessagePromise(), 2000);
        return Observable
            .interval(2000)
            .flatMap(() => this.getMessagePromise());
    }

    private handleError(error: any): Promise<any> {
        console.error('An error occurred', error); // for demo purposes only
        return Promise.reject(error.message || error);
    }
}