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
let DecoderService = class DecoderService {
    constructor(http) {
        this.http = http;
        this.headers = new http_1.Headers({ 'Content-Type': 'application/json' });
        this.decodersUrl = '/api/decoders/all';
        this.readDecoderUrl = '/api/decoders/read';
        this.fetchDecoderUrl = '/api/decoders/byId/';
        this.addFunctionUrl = '/api/decoders/function/add';
        this.deleteFunctionUrl = '/api/decoders/function/delete';
        this.linkMacroUrl = '/api/decoders/macro/link';
        this.unlinkMacroUrl = '/api/decoders/macro/unlink';
        this._decoders = new Rx_1.BehaviorSubject([]);
        this.decoders = this._decoders.asObservable();
        this._decoder = new Rx_1.BehaviorSubject(null);
        this.decoder = this._decoder.asObservable();
        this.fetchDecoders();
    }
    fetchDecoders() {
        this.http.get(this.decodersUrl).map(response => response.json()).subscribe(data => {
            this._decoders.next(data);
        }, error => console.log('Could not load decoders.'));
    }
    fetchDecoder(decoderId) {
        this.http.get(this.fetchDecoderUrl + decoderId).map(response => response.json()).subscribe(data => {
            this._decoder.next(data);
            this.fetchDecoders();
        }, error => console.log('Could not load decoder.'));
    }
    readDecoder() {
        this.http.get(this.readDecoderUrl).map(response => response.json()).subscribe(data => {
            this._decoder.next(data);
            this.fetchDecoders();
        }, error => console.log('Could not load decoder.'));
    }
    addDecoderFunction(decoderFunction) {
        this.http.post(this.addFunctionUrl, decoderFunction).map(response => response.json()).subscribe(data => {
            this._decoder.next(data);
        }, error => console.log('Could not load decoder.'));
    }
    deleteDecoderFunction(decoderFunction) {
        this.http.post(this.deleteFunctionUrl, decoderFunction).map(response => response.json()).subscribe(data => {
            this._decoder.next(data);
        }, error => console.log('Could not load decoder.'));
    }
    linkMacro(linkedMacro) {
        this.http.post(this.linkMacroUrl, linkedMacro).map(response => response.json()).subscribe(data => {
            this._decoder.next(data);
        }, error => console.log('Could not load decoder.'));
    }
    unlinkMacro(linkedMacro) {
        this.http.post(this.unlinkMacroUrl, linkedMacro).map(response => response.json()).subscribe(data => {
            this._decoder.next(data);
        }, error => console.log('Could not load decoder.'));
    }
    getDecoder() {
        return this.decoder;
    }
    getDecoders() {
        return this.decoders;
    }
};
DecoderService = __decorate([
    core_1.Injectable(),
    __metadata("design:paramtypes", [http_1.Http])
], DecoderService);
exports.DecoderService = DecoderService;
//# sourceMappingURL=decoder.service.js.map