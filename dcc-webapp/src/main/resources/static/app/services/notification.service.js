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
 * Created by shawn on 23/03/17.
 */
var core_1 = require("@angular/core");
var http_1 = require("@angular/http");
var Rx_1 = require("rxjs/Rx");
var NotificationService = (function () {
    function NotificationService(http) {
        this.http = http;
        this.headers = new http_1.Headers({ 'Content-Type': 'application/json' });
        this.notificationsUrl = '/notifications/';
        this._statusUpdates = new Rx_1.BehaviorSubject(null);
        this.statusUpdates = this._statusUpdates.asObservable();
        this._messageUpdates = new Rx_1.BehaviorSubject(null);
        this.messageUpdates = this._messageUpdates.asObservable();
        this._cabUpdates = new Rx_1.BehaviorSubject(null);
        this.cabUpdates = this._cabUpdates.asObservable();
        this._accessoryUpdates = new Rx_1.BehaviorSubject(null);
        this.accessoryUpdates = this._accessoryUpdates.asObservable();
        this.notificationId = -1;
        this.startFetchingNotifications();
    }
    NotificationService.prototype.processNotifications = function (data) {
        var status = false;
        var messages = false;
        var accessories = false;
        var cabList = new Array();
        for (var _i = 0, data_1 = data; _i < data_1.length; _i++) {
            var notification = data_1[_i];
            if ("STATUS" == notification.type) {
                status = true;
            }
            else if ("MESSAGES" == notification.type) {
                messages = true;
            }
            else if ("ACCESSORY" == notification.type) {
                accessories = true;
            }
            else if ("CAB" == notification.type) {
                cabList.push(Number(notification.value));
            }
            if (notification.notificationId > this.notificationId) {
                this.notificationId = notification.notificationId;
            }
        }
        if (status) {
            this._statusUpdates.next("StatusUpdate");
        }
        if (messages) {
            this._messageUpdates.next("MessageUpdate");
        }
        if (accessories) {
            this._accessoryUpdates.next("AccessoryUpdate");
        }
        if (cabList.length > 0) {
            this._cabUpdates.next(cabList);
        }
    };
    NotificationService.prototype.fetchNotifications = function () {
        var _this = this;
        this.http.get(this.notificationsUrl + this.notificationId)
            .map(function (response) { return response.json(); })
            .subscribe(function (data) {
            _this.processNotifications(data);
        }, function (error) { return console.log("Could not fetch notifications."); });
    };
    NotificationService.prototype.getStatusUpdates = function () {
        return this.statusUpdates;
    };
    NotificationService.prototype.getMessageUpdates = function () {
        return this.messageUpdates;
    };
    NotificationService.prototype.getCabUpdates = function () {
        return this.cabUpdates;
    };
    NotificationService.prototype.getAccessoryUpdates = function () {
        return this.accessoryUpdates;
    };
    NotificationService.prototype.startFetchingNotifications = function () {
        var _this = this;
        Rx_1.Observable.interval(1000).subscribe(function (data) { return _this.fetchNotifications(); });
    };
    NotificationService = __decorate([
        core_1.Injectable(), 
        __metadata('design:paramtypes', [http_1.Http])
    ], NotificationService);
    return NotificationService;
}());
exports.NotificationService = NotificationService;
//# sourceMappingURL=notification.service.js.map