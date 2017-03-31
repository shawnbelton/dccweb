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
 * Created by shawn on 23/11/16.
 */
var core_1 = require("@angular/core");
var http_1 = require("@angular/http");
var Rx_1 = require("rxjs/Rx");
var notification_service_1 = require("./notification.service");
var CabService = (function () {
    function CabService(http, notificationService) {
        var _this = this;
        this.http = http;
        this.notificationService = notificationService;
        this.headers = new http_1.Headers({ 'Content-Type': 'application/json' });
        this.fetchCabUrl = '/trains/cab';
        this.updateCabUrl = '/trains/cab/update';
        this.updateCabFunctionUrl = '/trains/cab/updateFunction';
        this._cab = new Rx_1.BehaviorSubject(null);
        this.cab = this._cab.asObservable();
        this.notificationService.getCabUpdates().subscribe(function (data) { return _this.checkUpdates(data); });
    }
    CabService.prototype.checkUpdates = function (cabList) {
        var inList = false;
        var current = this._cab.getValue();
        if (null != current) {
            for (var _i = 0, cabList_1 = cabList; _i < cabList_1.length; _i++) {
                var trainId = cabList_1[_i];
                if (trainId == current.train.trainId) {
                    inList = true;
                }
            }
            if (inList) {
                this.setTrain(current.train);
            }
        }
    };
    CabService.prototype.updateCab = function (cab) {
        var _this = this;
        this.http.post(this.updateCabUrl, cab).map(function (response) { return response.json(); }).subscribe(function (data) { return _this.response = data; });
    };
    CabService.prototype.updateCabFunction = function (cab) {
        var _this = this;
        this.http.post(this.updateCabFunctionUrl, cab).map(function (response) { return response.json(); }).subscribe(function (data) { return _this.response = data; });
    };
    CabService.prototype.setTrain = function (train) {
        var _this = this;
        this.http.post(this.fetchCabUrl, train).map(function (response) { return response.json(); }).subscribe(function (data) {
            _this._cab.next(data);
        }, function (error) { return console.log('Could not load cab.'); });
    };
    CabService.prototype.getCab = function () {
        return this.cab;
    };
    CabService = __decorate([
        core_1.Injectable(), 
        __metadata('design:paramtypes', [http_1.Http, notification_service_1.NotificationService])
    ], CabService);
    return CabService;
}());
exports.CabService = CabService;
//# sourceMappingURL=cab.service.js.map