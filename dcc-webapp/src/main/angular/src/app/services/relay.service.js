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
 * Created by shawn on 07/06/17.
 */
const core_1 = require("@angular/core");
const http_1 = require("@angular/http");
const notification_service_1 = require("./notification.service");
const Rx_1 = require("rxjs/Rx");
let RelayService = class RelayService {
    constructor(http, notificationService) {
        this.http = http;
        this.notificationService = notificationService;
        this.headers = new http_1.Headers({ 'Content-Type': 'application/json' });
        this.allRelayControllersUrl = '/api/relay-controller/all';
        this.saveUrl = '/api/relay-controller/save';
        this.updateUrl = '/api/relay-controller/update-value';
        this._relayControllers = new Rx_1.BehaviorSubject(null);
        this.relayControllers = this._relayControllers.asObservable();
        this.fetchRelayControllers();
        this.notificationService.getRelayUpdates().subscribe(data => this.fetchRelayControllers());
    }
    fetchRelayControllers() {
        this.http.get(this.allRelayControllersUrl).map(response => response.json()).subscribe(data => {
            this._relayControllers.next(data);
        }, error => console.log('Could not load relay controllers.'));
    }
    saveRelayController(relayController) {
        this.http.post(this.saveUrl, relayController).map(response => response.json()).subscribe(data => {
            this._relayControllers.next(data);
        }, error => console.log('Could not load relay controllers.'));
    }
    updateRelayValue(relayController) {
        this.http.post(this.updateUrl, relayController).map(response => response.json()).subscribe(data => {
            this._relayControllers.next(data);
        }, error => console.log('Could not load relay controllers.'));
    }
    getRelayControllers() {
        return this.relayControllers;
    }
};
RelayService = __decorate([
    core_1.Injectable(),
    __metadata("design:paramtypes", [http_1.Http, notification_service_1.NotificationService])
], RelayService);
exports.RelayService = RelayService;
//# sourceMappingURL=relay.service.js.map