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
 * Created by shawn on 02/12/16.
 */
const core_1 = require("@angular/core");
const Rx_1 = require("rxjs/Rx");
const macro_1 = require("../models/macro");
const http_1 = require("@angular/http");
let MacroService = class MacroService {
    constructor(http) {
        this.http = http;
        this._macros = new Rx_1.BehaviorSubject([]);
        this.macros = this._macros.asObservable();
        this._macro = new Rx_1.BehaviorSubject(new macro_1.Macro());
        this.macro = this._macro.asObservable();
        this.fetchMacros();
    }
    editMacro(macro) {
        this.http.get("/api/macros/" + macro.macroId.toString()).map(response => response.json())
            .subscribe(data => {
            this._macro.next(data);
        }, error => console.log("Unable to get macro."));
    }
    fetchMacros() {
        this.http.get("/api/macros/all").map(response => response.json()).subscribe(data => {
            this._macros.next(data);
        }, error => console.log("Unable to get macros."));
    }
    saveMacro(macro) {
        this.http.post("/api/macros/save", macro).map(response => response.json()).subscribe(data => {
            this._macros.next(data);
        }, error => console.log("Unable to get macros."));
    }
    deleteMacro(macro) {
        this.http.post("/api/macros/delete", macro).map(response => response.json()).subscribe(data => {
            this._macros.next(data);
        }, error => console.log("Unable to get macros."));
    }
    runMacro(macro) {
        this.http.post("/api/macros/run", macro).map(response => response.json()).subscribe(data => { }, error => console.log("Unable to run macro."));
    }
    getMacro() {
        return this.macro;
    }
    getMacros() {
        return this.macros;
    }
};
MacroService = __decorate([
    core_1.Injectable(),
    __metadata("design:paramtypes", [http_1.Http])
], MacroService);
exports.MacroService = MacroService;
//# sourceMappingURL=macro.service.js.map