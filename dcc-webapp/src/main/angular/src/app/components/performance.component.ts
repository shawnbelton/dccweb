/**
 * Created by shawn on 10/01/17.
 */
import {Component, OnInit} from '@angular/core';
import {PerformanceService} from '../services/performance.service';
import {Metrics} from '../models/metrics';

@Component({
    moduleId: module.id,
    templateUrl: './../html/performance/performance.html'
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
