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
 * Created by shawn on 19/11/16.
 */
var core_1 = require("@angular/core");
var http_1 = require("@angular/http");
var Rx_1 = require("rxjs/Rx");
require("rxjs/add/operator/toPromise");
var TrainService = (function () {
    function TrainService(http) {
        this.http = http;
        this.headers = new http_1.Headers({ 'Content-Type': 'application/json' });
        this.trainsUrl = '/trains';
        this.saveUrl = '/trains/save';
        this._trains = new Rx_1.BehaviorSubject([]);
        this.trains = this._trains.asObservable();
        this.loadInitialData();
    }
    TrainService.prototype.loadInitialData = function () {
        var _this = this;
        this.http.get(this.trainsUrl).map(function (response) { return response.json(); }).subscribe(function (data) {
            _this._trains.next(data);
        }, function (error) { return console.log('Could not load trains.'); });
    };
    TrainService.prototype.saveTrain = function (train) {
        var _this = this;
        this.http.post(this.saveUrl, train).map(function (response) { return response.json(); }).subscribe(function (data) {
            _this._trains.next(data);
        }, function (error) { return console.log('Could not load trains.'); });
    };
    TrainService.prototype.getTrains = function () {
        return this.trains;
    };
    TrainService = __decorate([
        core_1.Injectable(), 
        __metadata('design:paramtypes', [http_1.Http])
    ], TrainService);
    return TrainService;
}());
exports.TrainService = TrainService;
//# sourceMappingURL=train.service.js.map