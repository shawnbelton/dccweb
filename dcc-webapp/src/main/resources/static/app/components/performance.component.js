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
var performance_service_1 = require("../services/performance.service");
var PerformanceComponent = (function () {
    function PerformanceComponent(performanceService) {
        this.performanceService = performanceService;
    }
    PerformanceComponent.prototype.getPerformance = function () {
        var _this = this;
        this.performanceService.getMetrics().subscribe(function (metrics) { return _this.setMetrics(metrics); });
    };
    PerformanceComponent.prototype.setMetrics = function (metrics) {
        this.metrics = metrics;
    };
    PerformanceComponent.prototype.ngOnInit = function () {
        this.getPerformance();
    };
    PerformanceComponent = __decorate([
        core_1.Component({
            moduleId: module.id,
            templateUrl: '../../performance/performance.html'
        }), 
        __metadata('design:paramtypes', [performance_service_1.PerformanceService])
    ], PerformanceComponent);
    return PerformanceComponent;
}());
exports.PerformanceComponent = PerformanceComponent;
//# sourceMappingURL=performance.component.js.map