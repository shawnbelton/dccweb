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
 * Created by shawn on 23/11/16.
 */
const core_1 = require("@angular/core");
const http_1 = require("@angular/http");
const Rx_1 = require("rxjs/Rx");
const notification_service_1 = require("./notification.service");
let CabService = class CabService {
    constructor(http, notificationService) {
        this.http = http;
        this.notificationService = notificationService;
        this.headers = new http_1.Headers({ 'Content-Type': 'application/json' });
        this.fetchCabUrl = '/api/trains/cab';
        this.updateCabUrl = '/api/trains/cab/update';
        this.updateCabFunctionUrl = '/api/trains/cab/updateFunction';
        this._cab = new Rx_1.BehaviorSubject(null);
        this.cab = this._cab.asObservable();
        this.notificationService.getCabUpdates().subscribe(data => this.checkUpdates(data));
    }
    checkUpdates(cabList) {
        let inList = false;
        let current = this._cab.getValue();
        if (null != current) {
            for (let trainId of cabList) {
                if (trainId == current.train.trainId) {
                    inList = true;
                }
            }
            if (inList) {
                this.setTrain(current.train);
            }
        }
    }
    updateCab(cab) {
        this.http.post(this.updateCabUrl, cab).map(response => response.json()).subscribe(data => this.response = data);
    }
    updateCabFunction(cab) {
        this.http.post(this.updateCabFunctionUrl, cab).map(response => response.json()).subscribe(data => this.response = data);
    }
    setTrain(train) {
        this.http.post(this.fetchCabUrl, train).map(response => response.json()).subscribe(data => {
            this._cab.next(data);
        }, error => console.log('Could not load cab.'));
    }
    getCab() {
        return this.cab;
    }
};
CabService = __decorate([
    core_1.Injectable(),
    __metadata("design:paramtypes", [http_1.Http, notification_service_1.NotificationService])
], CabService);
exports.CabService = CabService;
//# sourceMappingURL=cab.service.js.map