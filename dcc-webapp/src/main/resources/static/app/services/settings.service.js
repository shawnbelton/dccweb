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
 * Created by shawn on 26/11/16.
 */
var core_1 = require("@angular/core");
var http_1 = require("@angular/http");
var settings_1 = require("../models/settings");
var Rx_1 = require("rxjs/Rx");
var SettingsService = (function () {
    function SettingsService(http) {
        this.http = http;
        this.headers = new http_1.Headers({ 'Content-Type': 'application/json' });
        this.settingsUrl = '/application/settings';
        this.interfacesUrl = '/interfaces';
        this._settings = new Rx_1.BehaviorSubject(new settings_1.Settings());
        this.settings = this._settings.asObservable();
        this._interfaces = new Rx_1.BehaviorSubject([]);
        this.interfaces = this._interfaces.asObservable();
        this.loadSettings();
        this.loadInterfaces();
    }
    SettingsService.prototype.loadSettings = function () {
        var _this = this;
        this.http.get(this.settingsUrl).map(function (response) { return response.json(); }).subscribe(function (data) {
            _this._settings.next(data);
        }, function (error) { return console.log('Could not load settings.'); });
    };
    SettingsService.prototype.loadInterfaces = function () {
        var _this = this;
        this.http.get(this.interfacesUrl).map(function (response) { return response.json(); }).subscribe(function (data) {
            _this._interfaces.next(data);
        }, function (error) { return console.log('Could not load interfaces.'); });
    };
    SettingsService.prototype.saveSettings = function (settings) {
        var _this = this;
        this.http.post(this.settingsUrl, settings).map(function (response) { return response.json(); }).subscribe(function (data) {
            _this._settings.next(data);
        }, function (error) { return console.log('Could not load settings.'); });
    };
    SettingsService.prototype.getSettings = function () {
        return this.settings;
    };
    SettingsService.prototype.getInterfaces = function () {
        return this.interfaces;
    };
    SettingsService = __decorate([
        core_1.Injectable(), 
        __metadata('design:paramtypes', [http_1.Http])
    ], SettingsService);
    return SettingsService;
}());
exports.SettingsService = SettingsService;
//# sourceMappingURL=settings.service.js.map