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
/**
 * Created by shawn on 10/01/17.
 */
var core_1 = require("@angular/core");
var http_1 = require("@angular/http");
var Rx_1 = require("rxjs/Rx");
var metrics_1 = require("../models/metrics");
var PerformanceService = (function () {
    function PerformanceService(http) {
        this.http = http;
        this.headers = new http_1.Headers({ 'Content-Type': 'application/json' });
        this.metricsUrl = '/metrics';
        this._metrics = new Rx_1.BehaviorSubject([]);
        this.metrics = this._metrics.asObservable();
        this.startFetchingMetrics();
    }
    PerformanceService.prototype.fetchMetrics = function () {
        var _this = this;
        this.http.get(this.metricsUrl).subscribe(function (data) {
            _this.processMetrics(data);
        }, function (error) { return console.log('Could not get metrics.'); });
    };
    PerformanceService.prototype.processMetrics = function (data) {
        this._metrics.next(JSON.parse(data.text(), metrics_1.Metrics.reviver));
    };
    PerformanceService.prototype.getMetrics = function () {
        return this.metrics;
    };
    PerformanceService.prototype.startFetchingMetrics = function () {
        var _this = this;
        Rx_1.Observable.interval(1000).subscribe(function (data) { return _this.fetchMetrics(); });
    };
    PerformanceService = __decorate([
        core_1.Injectable(), 
        __metadata('design:paramtypes', [http_1.Http])
    ], PerformanceService);
    return PerformanceService;
}());
exports.PerformanceService = PerformanceService;
//# sourceMappingURL=performance.service.js.map