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
 * Created by shawn on 03/04/17.
 */
const core_1 = require("@angular/core");
const http_1 = require("@angular/http");
const notification_service_1 = require("./notification.service");
const Rx_1 = require("rxjs/Rx");
let BlockService = class BlockService {
    constructor(http, notificationService) {
        this.http = http;
        this.notificationService = notificationService;
        this.headers = new http_1.Headers({ 'Content-Type': 'application/json' });
        this.blockUrl = '/api/block/all';
        this.saveBlockUrl = '/api/block/save';
        this.deleteBlockUrl = '/api/block/delete';
        this.blockOccupiedUrl = '/api/block/occupied';
        this._blocks = new Rx_1.BehaviorSubject(null);
        this.blocks = this._blocks.asObservable();
        this.fetchBlocks();
        this.notificationService.getBlockUpdates().subscribe(data => this.fetchBlocks());
    }
    fetchBlocks() {
        this.http.get(this.blockUrl).map(response => response.json()).subscribe(data => {
            this._blocks.next(data);
        }, error => console.log('Could not load blocks.'));
    }
    saveBlock(block) {
        this.http.post(this.saveBlockUrl, block).map(response => response.json()).subscribe(data => {
            this._blocks.next(data);
        }, error => console.log('Could not load blocks.'));
    }
    deleteBlock(block) {
        this.http.post(this.deleteBlockUrl, block).map(response => response.json()).subscribe(data => {
            this._blocks.next(data);
        }, error => console.log('Could not load blocks.'));
    }
    setBlockOccupancy(block) {
        this.http.post(this.blockOccupiedUrl, block).map(response => response.json()).subscribe(data => {
            this._blocks.next(data);
        }, error => console.log('Could not load blocks.'));
    }
    getBlocks() {
        return this.blocks;
    }
};
BlockService = __decorate([
    core_1.Injectable(),
    __metadata("design:paramtypes", [http_1.Http, notification_service_1.NotificationService])
], BlockService);
exports.BlockService = BlockService;
//# sourceMappingURL=block.service.js.map