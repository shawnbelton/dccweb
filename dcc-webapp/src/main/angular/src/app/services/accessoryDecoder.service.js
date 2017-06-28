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
 * Created by shawn on 24/02/17.
 */
const core_1 = require("@angular/core");
const http_1 = require("@angular/http");
const Rx_1 = require("rxjs/Rx");
const notification_service_1 = require("./notification.service");
let AccessoryDecoderService = class AccessoryDecoderService {
    constructor(http, notificationService) {
        this.http = http;
        this.notificationService = notificationService;
        this.headers = new http_1.Headers({ 'Content-Type': 'application/json' });
        this.fetchAccessoryTypeUrl = '/api/accessory/decoder/type/all';
        this.fetchAccessoriesUrl = '/api/accessory/decoder/all';
        this.saveAccessoryUrl = '/api/accessory/decoder/save';
        this.operateAccessoryUrl = '/api/accessory/decoder/operate';
        this._accessoryTypes = new Rx_1.BehaviorSubject(null);
        this.accessoryTypes = this._accessoryTypes.asObservable();
        this._accessories = new Rx_1.BehaviorSubject(null);
        this.accessories = this._accessories.asObservable();
        this._accessory = new Rx_1.BehaviorSubject(null);
        this.accessory = this._accessory.asObservable();
        this.fetchAccessoryTypes();
        this.fetchAccessories();
        this.notificationService.getAccessoryUpdates().subscribe(data => this.fetchAccessories());
    }
    fetchAccessoryTypes() {
        this.http.get(this.fetchAccessoryTypeUrl).map(response => response.json()).subscribe(data => {
            this._accessoryTypes.next(data);
        }, error => console.log('Could not load accessory types.'));
    }
    fetchAccessories() {
        this.http.get(this.fetchAccessoriesUrl).map(response => response.json()).subscribe(data => {
            this._accessories.next(data);
        }, error => console.log('Could not load accessories.'));
    }
    saveAccessory(accessory) {
        this.http.post(this.saveAccessoryUrl, accessory).map(response => response.json()).subscribe(data => {
            this._accessories.next(data);
        }, error => console.log('Could not load accessories.'));
    }
    operateAccessory(accessoryOperation) {
        let retval = false;
        this.http.post(this.operateAccessoryUrl, accessoryOperation).map(response => response.json())
            .subscribe(data => retval = data, error => console.log('Could not operate accessory.'));
    }
    getAccessories() {
        return this.accessories;
    }
    getAccessoryTypes() {
        return this.accessoryTypes;
    }
    getAccessory() {
        return this.accessory;
    }
};
AccessoryDecoderService = __decorate([
    core_1.Injectable(),
    __metadata("design:paramtypes", [http_1.Http, notification_service_1.NotificationService])
], AccessoryDecoderService);
exports.AccessoryDecoderService = AccessoryDecoderService;
//# sourceMappingURL=accessoryDecoder.service.js.map