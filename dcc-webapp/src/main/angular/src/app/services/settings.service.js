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
 * Created by shawn on 26/11/16.
 */
const core_1 = require("@angular/core");
const http_1 = require("@angular/http");
const settings_1 = require("../models/settings");
const Rx_1 = require("rxjs/Rx");
let SettingsService = class SettingsService {
    constructor(http) {
        this.http = http;
        this.headers = new http_1.Headers({ 'Content-Type': 'application/json' });
        this.settingsUrl = '/api/application/settings';
        this.interfacesUrl = '/api/interface/all';
        this._settings = new Rx_1.BehaviorSubject(new settings_1.Settings());
        this.settings = this._settings.asObservable();
        this._interfaces = new Rx_1.BehaviorSubject([]);
        this.interfaces = this._interfaces.asObservable();
        this.loadSettings();
        this.loadInterfaces();
    }
    loadSettings() {
        this.http.get(this.settingsUrl).map(response => response.json()).subscribe(data => {
            this._settings.next(data);
        }, error => console.log('Could not load settings.'));
    }
    loadInterfaces() {
        this.http.get(this.interfacesUrl).map(response => response.json()).subscribe(data => {
            this._interfaces.next(data);
        }, error => console.log('Could not load interfaces.'));
    }
    saveSettings(settings) {
        this.http.post(this.settingsUrl, settings).map(response => response.json()).subscribe(data => {
            this._settings.next(data);
        }, error => console.log('Could not load settings.'));
    }
    getSettings() {
        return this.settings;
    }
    getInterfaces() {
        return this.interfaces;
    }
};
SettingsService = __decorate([
    core_1.Injectable(),
    __metadata("design:paramtypes", [http_1.Http])
], SettingsService);
exports.SettingsService = SettingsService;
//# sourceMappingURL=settings.service.js.map