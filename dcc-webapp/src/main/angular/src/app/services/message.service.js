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
 * Created by shawn on 16/11/16.
 */
const core_1 = require("@angular/core");
const http_1 = require("@angular/http");
const Rx_1 = require("rxjs/Rx");
const notification_service_1 = require("./notification.service");
let MessageService = class MessageService {
    constructor(http, notificationService) {
        this.http = http;
        this.notificationService = notificationService;
        this.headers = new http_1.Headers({ 'Content-Type': 'application/json' });
        this.logEntryUrl = '/api/messages';
        this._messages = new Rx_1.BehaviorSubject([]);
        this.messages = this._messages.asObservable();
        this.notificationService.getMessageUpdates().subscribe(data => this.fetchMessages());
    }
    fetchMessages() {
        this.http.get(this.logEntryUrl).map(response => response.json()).subscribe(data => {
            this._messages.next(data);
        }, error => console.log('Could not get messages.'));
    }
    getMessages() {
        return this.messages;
    }
};
MessageService = __decorate([
    core_1.Injectable(),
    __metadata("design:paramtypes", [http_1.Http, notification_service_1.NotificationService])
], MessageService);
exports.MessageService = MessageService;
//# sourceMappingURL=message.service.js.map