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
 * Created by shawn on 24/02/17.
 */
var core_1 = require("@angular/core");
var http_1 = require("@angular/http");
var Rx_1 = require("rxjs/Rx");
var notification_service_1 = require("./notification.service");
var AccessoryDecoderService = (function () {
    function AccessoryDecoderService(http, notificationService) {
        var _this = this;
        this.http = http;
        this.notificationService = notificationService;
        this.headers = new http_1.Headers({ 'Content-Type': 'application/json' });
        this.fetchAccessoryTypeUrl = '/accessory/decoder/type/all';
        this.fetchAccessoriesUrl = '/accessory/decoder/all';
        this.saveAccessoryUrl = '/accessory/decoder/save';
        this.operateAccessoryUrl = '/accessory/decoder/operate';
        this._accessoryTypes = new Rx_1.BehaviorSubject(null);
        this.accessoryTypes = this._accessoryTypes.asObservable();
        this._accessories = new Rx_1.BehaviorSubject(null);
        this.accessories = this._accessories.asObservable();
        this._accessory = new Rx_1.BehaviorSubject(null);
        this.accessory = this._accessory.asObservable();
        this.fetchAccessoryTypes();
        this.fetchAccessories();
        this.notificationService.getAccessoryUpdates().subscribe(function (data) { return _this.fetchAccessories(); });
    }
    AccessoryDecoderService.prototype.fetchAccessoryTypes = function () {
        var _this = this;
        this.http.get(this.fetchAccessoryTypeUrl).map(function (response) { return response.json(); }).subscribe(function (data) {
            _this._accessoryTypes.next(data);
        }, function (error) { return console.log('Could not load accessory types.'); });
    };
    AccessoryDecoderService.prototype.fetchAccessories = function () {
        var _this = this;
        this.http.get(this.fetchAccessoriesUrl).map(function (response) { return response.json(); }).subscribe(function (data) {
            _this._accessories.next(data);
        }, function (error) { return console.log('Could not load accessories.'); });
    };
    AccessoryDecoderService.prototype.saveAccessory = function (accessory) {
        var _this = this;
        this.http.post(this.saveAccessoryUrl, accessory).map(function (response) { return response.json(); }).subscribe(function (data) {
            _this._accessories.next(data);
        }, function (error) { return console.log('Could not load accessories.'); });
    };
    AccessoryDecoderService.prototype.operateAccessory = function (accessoryOperation) {
        var retval = false;
        this.http.post(this.operateAccessoryUrl, accessoryOperation).map(function (response) { return response.json(); })
            .subscribe(function (data) { return retval = data; }, function (error) { return console.log('Could not operate accessory.'); });
    };
    AccessoryDecoderService.prototype.getAccessories = function () {
        return this.accessories;
    };
    AccessoryDecoderService.prototype.getAccessoryTypes = function () {
        return this.accessoryTypes;
    };
    AccessoryDecoderService.prototype.getAccessory = function () {
        return this.accessory;
    };
    AccessoryDecoderService = __decorate([
        core_1.Injectable(), 
        __metadata('design:paramtypes', [http_1.Http, notification_service_1.NotificationService])
    ], AccessoryDecoderService);
    return AccessoryDecoderService;
}());
exports.AccessoryDecoderService = AccessoryDecoderService;
//# sourceMappingURL=accessoryDecoder.service.js.map