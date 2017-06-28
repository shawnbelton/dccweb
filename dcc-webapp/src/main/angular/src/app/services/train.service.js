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
let TrainService = class TrainService {
    constructor(http) {
        this.http = http;
        this.headers = new http_1.Headers({ 'Content-Type': 'application/json' });
        this.trainsUrl = '/api/trains/all';
        this.saveUrl = '/api/trains/save';
        this._trains = new Rx_1.BehaviorSubject([]);
        this.trains = this._trains.asObservable();
        this.loadInitialData();
    }
    loadInitialData() {
        this.http.get(this.trainsUrl).map(response => response.json()).subscribe(data => {
            this._trains.next(data);
        }, error => console.log('Could not load trains.'));
    }
    saveTrain(train) {
        this.http.post(this.saveUrl, train).map(response => response.json()).subscribe(data => {
            this._trains.next(data);
        }, error => console.log('Could not load trains.'));
    }
    getTrains() {
        return this.trains;
    }
};
TrainService = __decorate([
    core_1.Injectable(),
    __metadata("design:paramtypes", [http_1.Http])
], TrainService);
exports.TrainService = TrainService;
//# sourceMappingURL=train.service.js.map