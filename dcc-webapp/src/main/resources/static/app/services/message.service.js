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
 * Created by shawn on 16/11/16.
 */
var core_1 = require("@angular/core");
var http_1 = require("@angular/http");
var Rx_1 = require("rxjs/Rx");
var notification_service_1 = require("./notification.service");
var MessageService = (function () {
    function MessageService(http, notificationService) {
        var _this = this;
        this.http = http;
        this.notificationService = notificationService;
        this.headers = new http_1.Headers({ 'Content-Type': 'application/json' });
        this.logEntryUrl = '/messages';
        this._messages = new Rx_1.BehaviorSubject([]);
        this.messages = this._messages.asObservable();
        this.notificationService.getMessageUpdates().subscribe(function (data) { return _this.fetchMessages(); });
    }
    MessageService.prototype.fetchMessages = function () {
        var _this = this;
        this.http.get(this.logEntryUrl).map(function (response) { return response.json(); }).subscribe(function (data) {
            _this._messages.next(data);
        }, function (error) { return console.log('Could not get messages.'); });
    };
    MessageService.prototype.getMessages = function () {
        return this.messages;
    };
    MessageService = __decorate([
        core_1.Injectable(), 
        __metadata('design:paramtypes', [http_1.Http, notification_service_1.NotificationService])
    ], MessageService);
    return MessageService;
}());
exports.MessageService = MessageService;
//# sourceMappingURL=message.service.js.map