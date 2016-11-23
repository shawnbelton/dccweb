/**
 * Created by shawn on 19/11/16.
 */
import {Injectable} from "@angular/core";
import {Headers, Http} from "@angular/http";
import {Observable} from "rxjs/Rx";
import "rxjs/add/operator/toPromise";
import {Status} from "./status";

@Injectable()
export class StatusService {

    private headers = new Headers({'Content-Type': 'application/json'});
    private statusUrl = '/interface/status';

    constructor(private http: Http) {}

    getStatusPromise(): Promise<Status> {
        return this.http.get(this.statusUrl)
            .toPromise()
            .then(response => response.json() as Status)
            .catch(this.handleError);
    }

    getStatus(): Observable<Status> {
        return Observable
            .interval(2000)
            .flatMap(() => this.getStatusPromise());
    }

    private handleError(error: any): Promise<any> {
        console.error('An error occurred', error); // for demo purposes only
        return Promise.reject(error.message || error);
    }
}