"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
Object.defineProperty(exports, "__esModule", { value: true });
/**
 * Created by shawn on 10/01/17.
 */
const core_1 = require("@angular/core");
const http_1 = require("@angular/http");
const Rx_1 = require("rxjs/Rx");
const metrics_1 = require("../models/metrics");
let PerformanceService = class PerformanceService {
    constructor(http) {
        this.http = http;
        this.headers = new http_1.Headers({ 'Content-Type': 'application/json' });
        this.metricsUrl = '/metrics';
        this._metrics = new Rx_1.BehaviorSubject([]);
        this.metrics = this._metrics.asObservable();
        this.startFetchingMetrics();
    }
    fetchMetrics() {
        this.http.get(this.metricsUrl).subscribe(data => {
            this.processMetrics(data);
        }, error => console.log('Could not get metrics.'));
    }
    processMetrics(data) {
        this._metrics.next(JSON.parse(data.text(), metrics_1.Metrics.reviver));
    }
    getMetrics() {
        return this.metrics;
    }
    startFetchingMetrics() {
        Rx_1.Observable.interval(1000).subscribe(data => this.fetchMetrics());
    }
};
PerformanceService = __decorate([
    core_1.Injectable(),
    __metadata("design:paramtypes", [http_1.Http])
], PerformanceService);
exports.PerformanceService = PerformanceService;
//# sourceMappingURL=performance.service.js.map