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
 * Created by shawn on 19/11/16.
 */
const core_1 = require("@angular/core");
const http_1 = require("@angular/http");
const Rx_1 = require("rxjs/Rx");
require("rxjs/add/operator/toPromise");
const status_1 = require("../models/status");
const notification_service_1 = require("./notification.service");
let StatusService = class StatusService {
    constructor(http, notificationService) {
        this.http = http;
        this.notificationService = notificationService;
        this.headers = new http_1.Headers({ 'Content-Type': 'application/json' });
        this.statusUrl = '/api/interface/status';
        this._status = new Rx_1.BehaviorSubject(new status_1.Status());
        this.status = this._status.asObservable();
        this.notificationService.getStatusUpdates().subscribe(data => this.readStatus());
    }
    readStatus() {
        this.http.get(this.statusUrl).map(response => response.json())
            .subscribe(data => {
            this._status.next(data);
        }, error => this.unableToReadStatus());
    }
    unableToReadStatus() {
        let status = new status_1.Status();
        status.status = "Service Down";
        this._status.next(status);
    }
    getStatus() {
        return this.status;
    }
};
StatusService = __decorate([
    core_1.Injectable(),
    __metadata("design:paramtypes", [http_1.Http, notification_service_1.NotificationService])
], StatusService);
exports.StatusService = StatusService;
//# sourceMappingURL=status.service.js.map