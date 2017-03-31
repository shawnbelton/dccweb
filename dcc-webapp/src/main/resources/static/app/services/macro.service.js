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
 * Created by shawn on 02/12/16.
 */
var core_1 = require("@angular/core");
var Rx_1 = require("rxjs/Rx");
var macro_1 = require("../models/macro");
var http_1 = require("@angular/http");
var MacroService = (function () {
    function MacroService(http) {
        this.http = http;
        this._macros = new Rx_1.BehaviorSubject([]);
        this.macros = this._macros.asObservable();
        this._macro = new Rx_1.BehaviorSubject(new macro_1.Macro());
        this.macro = this._macro.asObservable();
        this.fetchMacros();
    }
    MacroService.prototype.editMacro = function (macro) {
        var _this = this;
        this.http.get("/macros/" + macro.macroId.toString()).map(function (response) { return response.json(); })
            .subscribe(function (data) {
            _this._macro.next(data);
        }, function (error) { return console.log("Unable to get macro."); });
    };
    MacroService.prototype.fetchMacros = function () {
        var _this = this;
        this.http.get("/macros").map(function (response) { return response.json(); }).subscribe(function (data) {
            _this._macros.next(data);
        }, function (error) { return console.log("Unable to get macros."); });
    };
    MacroService.prototype.saveMacro = function (macro) {
        var _this = this;
        this.http.post("/macros/save", macro).map(function (response) { return response.json(); }).subscribe(function (data) {
            _this._macros.next(data);
        }, function (error) { return console.log("Unable to get macros."); });
    };
    MacroService.prototype.deleteMacro = function (macro) {
        var _this = this;
        this.http.post("/macros/delete", macro).map(function (response) { return response.json(); }).subscribe(function (data) {
            _this._macros.next(data);
        }, function (error) { return console.log("Unable to get macros."); });
    };
    MacroService.prototype.runMacro = function (macro) {
        this.http.post("/macros/run", macro).map(function (response) { return response.json(); }).subscribe(function (data) { }, function (error) { return console.log("Unable to run macro."); });
    };
    MacroService.prototype.getMacro = function () {
        return this.macro;
    };
    MacroService.prototype.getMacros = function () {
        return this.macros;
    };
    MacroService = __decorate([
        core_1.Injectable(), 
        __metadata('design:paramtypes', [http_1.Http])
    ], MacroService);
    return MacroService;
}());
exports.MacroService = MacroService;
//# sourceMappingURL=macro.service.js.map