/**
 * Created by shawn on 10/01/17.
 */
import {Injectable} from "@angular/core";
import {BehaviorSubject, interval, Observable} from "rxjs";
import {Metrics} from "../models/metrics";
import {HttpClient} from "@angular/common/http";

@Injectable()
export class PerformanceService {

    private metricsUrl = '/metrics';

    private _metrics: BehaviorSubject<Metrics[]> = new BehaviorSubject([]);
    private metrics: Observable<Metrics[]> = this._metrics.asObservable();

    constructor(private http: HttpClient) {
        this.startFetchingMetrics();
    }

    fetchMetrics(): void {
        this.http.get(this.metricsUrl).subscribe((data: Metrics[]) => {
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
        interval(1000).subscribe(data => this.fetchMetrics());
    }

}
