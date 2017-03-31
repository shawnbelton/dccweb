/**
 * Created by shawn on 10/01/17.
 */
import {Injectable} from "@angular/core";
import {Headers, Http} from "@angular/http";
import {BehaviorSubject, Observable} from "rxjs/Rx";
import {Metrics} from "../models/metrics";

@Injectable()
export class PerformanceService {

    private headers = new Headers({'Content-Type': 'application/json'});
    private metricsUrl = '/metrics';

    private _metrics: BehaviorSubject<Metrics[]> = new BehaviorSubject([]);
    private metrics: Observable<Metrics[]> = this._metrics.asObservable();

    constructor(private http: Http) {
        this.startFetchingMetrics();
    }

    fetchMetrics(): void {
        this.http.get(this.metricsUrl).subscribe(data => {
            this.processMetrics(data);
        }, error => console.log('Could not get metrics.'));
    }

    processMetrics(data): void {
        this._metrics.next(JSON.parse(data.text(), Metrics.reviver));
    }

    getMetrics(): Observable<Metrics[]> {
        return this.metrics;
    }

    startFetchingMetrics(): void {
        Observable.interval(1000).subscribe(data => this.fetchMetrics());
    }

}