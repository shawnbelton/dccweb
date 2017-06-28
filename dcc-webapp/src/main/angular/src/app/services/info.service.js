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
 * Created by shawn on 12/01/17.
 */
const http_1 = require("@angular/http");
const Rx_1 = require("rxjs/Rx");
const info_1 = require("../models/info");
const core_1 = require("@angular/core");
let InfoService = class InfoService {
    constructor(http) {
        this.http = http;
        this.headers = new http_1.Headers({ 'Content-Type': 'application/json' });
        this.infoUrl = '/info';
        this._info = new Rx_1.BehaviorSubject(new info_1.Info());
        this.info = this._info.asObservable();
        this.fetchInfo();
    }
    fetchInfo() {
        this.http.get(this.infoUrl).map(response => response.json()).subscribe(data => {
            this.setInfo(data);
        }, error => console.log('Could not load info.'));
    }
    setInfo(data) {
        this._info.next(data);
    }
    getInfo() {
        return this.info;
    }
};
InfoService = __decorate([
    core_1.Injectable(),
    __metadata("design:paramtypes", [http_1.Http])
], InfoService);
exports.InfoService = InfoService;
//# sourceMappingURL=info.service.js.map