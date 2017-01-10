/**
 * Created by shawn on 10/01/17.
 */
import {OnInit, Component} from "@angular/core";
import {PerformanceService} from "./performance.service";
import {Metrics} from "./metrics";

@Component({
    moduleId: module.id,
    templateUrl: '/performance/performance.html'
})
export class PerformanceComponent implements OnInit {

    metrics: Metrics[];

    constructor(private performanceService: PerformanceService) {}

    getPerformance(): void {
        this.performanceService.getMetrics().subscribe(metrics => this.setMetrics(metrics));
    }

    setMetrics(metrics: Metrics[]): void {
        this.metrics = metrics;
    }

    ngOnInit(): void {
        this.getPerformance();
    }
}