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
/**
 * Created by shawn on 19/11/16.
 */
var core_1 = require("@angular/core");
var http_1 = require("@angular/http");
var Rx_1 = require("rxjs/Rx");
var DecoderService = (function () {
    function DecoderService(http) {
        this.http = http;
        this.headers = new http_1.Headers({ 'Content-Type': 'application/json' });
        this.decodersUrl = '/decoders/all';
        this.readDecoderUrl = '/decoders/read';
        this.fetchDecoderUrl = '/decoders/byId/';
        this.addFunctionUrl = '/decoders/function/add';
        this.deleteFunctionUrl = '/decoders/function/delete';
        this.linkMacroUrl = '/decoders/macro/link';
        this.unlinkMacroUrl = '/decoders/macro/unlink';
        this._decoders = new Rx_1.BehaviorSubject([]);
        this.decoders = this._decoders.asObservable();
        this._decoder = new Rx_1.BehaviorSubject(null);
        this.decoder = this._decoder.asObservable();
        this.fetchDecoders();
    }
    DecoderService.prototype.fetchDecoders = function () {
        var _this = this;
        this.http.get(this.decodersUrl).map(function (response) { return response.json(); }).subscribe(function (data) {
            _this._decoders.next(data);
        }, function (error) { return console.log('Could not load decoders.'); });
    };
    DecoderService.prototype.fetchDecoder = function (decoderId) {
        var _this = this;
        this.http.get(this.fetchDecoderUrl + decoderId).map(function (response) { return response.json(); }).subscribe(function (data) {
            _this._decoder.next(data);
            _this.fetchDecoders();
        }, function (error) { return console.log('Could not load decoder.'); });
    };
    DecoderService.prototype.readDecoder = function () {
        var _this = this;
        this.http.get(this.readDecoderUrl).map(function (response) { return response.json(); }).subscribe(function (data) {
            _this._decoder.next(data);
            _this.fetchDecoders();
        }, function (error) { return console.log('Could not load decoder.'); });
    };
    DecoderService.prototype.addDecoderFunction = function (decoderFunction) {
        var _this = this;
        this.http.post(this.addFunctionUrl, decoderFunction).map(function (response) { return response.json(); }).subscribe(function (data) {
            _this._decoder.next(data);
        }, function (error) { return console.log('Could not load decoder.'); });
    };
    DecoderService.prototype.deleteDecoderFunction = function (decoderFunction) {
        var _this = this;
        this.http.post(this.deleteFunctionUrl, decoderFunction).map(function (response) { return response.json(); }).subscribe(function (data) {
            _this._decoder.next(data);
        }, function (error) { return console.log('Could not load decoder.'); });
    };
    DecoderService.prototype.linkMacro = function (linkedMacro) {
        var _this = this;
        this.http.post(this.linkMacroUrl, linkedMacro).map(function (response) { return response.json(); }).subscribe(function (data) {
            _this._decoder.next(data);
        }, function (error) { return console.log('Could not load decoder.'); });
    };
    DecoderService.prototype.unlinkMacro = function (linkedMacro) {
        var _this = this;
        this.http.post(this.unlinkMacroUrl, linkedMacro).map(function (response) { return response.json(); }).subscribe(function (data) {
            _this._decoder.next(data);
        }, function (error) { return console.log('Could not load decoder.'); });
    };
    DecoderService.prototype.getDecoder = function () {
        return this.decoder;
    };
    DecoderService.prototype.getDecoders = function () {
        return this.decoders;
    };
    DecoderService = __decorate([
        core_1.Injectable(), 
        __metadata('design:paramtypes', [http_1.Http])
    ], DecoderService);
    return DecoderService;
}());
exports.DecoderService = DecoderService;
//# sourceMappingURL=decoder.service.js.map