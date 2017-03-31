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
 * Created by shawn on 22/11/16.
 */
var core_1 = require("@angular/core");
var cab_service_1 = require("../services/cab.service");
var macro_service_1 = require("../services/macro.service");
var CabComponent = (function () {
    function CabComponent(cabService, macroService) {
        this.cabService = cabService;
        this.macroService = macroService;
    }
    CabComponent.prototype.speedChange = function () {
        this.updateCab();
    };
    CabComponent.prototype.up = function () {
        this.cab.direction = 'UP';
        this.updateCab();
    };
    CabComponent.prototype.down = function () {
        this.cab.direction = 'DOWN';
        this.updateCab();
    };
    CabComponent.prototype.halt = function () {
        this.cab.speed = 0;
        this.updateCab();
    };
    CabComponent.prototype.stop = function () {
        if (this.cab.direction == 'UP') {
            this.cab.speed = 0;
            this.cab.direction = 'FSTOP';
        }
        else if (this.cab.direction == 'DOWN') {
            this.cab.speed = 0;
            this.cab.direction = 'RSTOP';
        }
        this.updateCab();
    };
    CabComponent.prototype.updateCab = function () {
        this.cabService.updateCab(this.cab);
    };
    CabComponent.prototype.updateCabFunction = function () {
        this.cabService.updateCabFunction(this.cab);
    };
    CabComponent.prototype.runMacro = function (macro) {
        this.macroService.runMacro(macro);
    };
    CabComponent.prototype.toggleFunction = function (cabFunction) {
        cabFunction.state = !cabFunction.state;
        this.updateCabFunction();
    };
    CabComponent.prototype.getCab = function () {
        var _this = this;
        this.cabService.getCab().subscribe(function (cab) { return _this.cab = cab; });
    };
    CabComponent.prototype.ngOnInit = function () {
        this.getCab();
    };
    CabComponent = __decorate([
        core_1.Component({
            moduleId: module.id,
            templateUrl: '../../cab/cab.html',
            selector: 'dcc-cab'
        }), 
        __metadata('design:paramtypes', [cab_service_1.CabService, macro_service_1.MacroService])
    ], CabComponent);
    return CabComponent;
}());
exports.CabComponent = CabComponent;
//# sourceMappingURL=cab.component.js.map