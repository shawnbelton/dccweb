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
 * Created by shawn on 23/03/17.
 */
const core_1 = require("@angular/core");
const http_1 = require("@angular/http");
const Rx_1 = require("rxjs/Rx");
let NotificationService = class NotificationService {
    constructor(http) {
        this.http = http;
        this.headers = new http_1.Headers({ 'Content-Type': 'application/json' });
        this.notificationsUrl = '/api/notifications/';
        this._statusUpdates = new Rx_1.BehaviorSubject(null);
        this.statusUpdates = this._statusUpdates.asObservable();
        this._messageUpdates = new Rx_1.BehaviorSubject(null);
        this.messageUpdates = this._messageUpdates.asObservable();
        this._blockUpdates = new Rx_1.BehaviorSubject(null);
        this.blockUpdates = this._blockUpdates.asObservable();
        this._relayUpdates = new Rx_1.BehaviorSubject(null);
        this.relayUpdates = this._relayUpdates.asObservable();
        this._cabUpdates = new Rx_1.BehaviorSubject(null);
        this.cabUpdates = this._cabUpdates.asObservable();
        this._accessoryUpdates = new Rx_1.BehaviorSubject(null);
        this.accessoryUpdates = this._accessoryUpdates.asObservable();
        this.notificationId = -1;
        this.startFetchingNotifications();
    }
    processNotifications(data) {
        let status = false;
        let messages = false;
        let accessories = false;
        let blocks = false;
        let relays = false;
        let cabList = new Array();
        for (let notification of data) {
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
            else if ("BLOCK" == notification.type) {
                blocks = true;
            }
            else if ("RELAY" == notification.type) {
                relays = true;
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
        if (blocks) {
            this._blockUpdates.next("BlockUpdate");
        }
        if (relays) {
            this._relayUpdates.next("RelayUpdates");
        }
        if (cabList.length > 0) {
            this._cabUpdates.next(cabList);
        }
    }
    fetchNotifications() {
        this.http.get(this.notificationsUrl + this.notificationId)
            .map(response => response.json())
            .subscribe(data => {
            this.processNotifications(data);
        }, error => console.log("Could not fetch notifications."));
    }
    getStatusUpdates() {
        return this.statusUpdates;
    }
    getMessageUpdates() {
        return this.messageUpdates;
    }
    getBlockUpdates() {
        return this.blockUpdates;
    }
    getRelayUpdates() {
        return this.relayUpdates;
    }
    getCabUpdates() {
        return this.cabUpdates;
    }
    getAccessoryUpdates() {
        return this.accessoryUpdates;
    }
    startFetchingNotifications() {
        Rx_1.Observable.interval(1000).subscribe(data => this.fetchNotifications());
    }
};
NotificationService = __decorate([
    core_1.Injectable(),
    __metadata("design:paramtypes", [http_1.Http])
], NotificationService);
exports.NotificationService = NotificationService;
//# sourceMappingURL=notification.service.js.map