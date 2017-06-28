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
 * Created by shawn on 22/11/16.
 */
const core_1 = require("@angular/core");
const cab_service_1 = require("../services/cab.service");
const macro_service_1 = require("../services/macro.service");
let CabComponent = class CabComponent {
    constructor(cabService, macroService) {
        this.cabService = cabService;
        this.macroService = macroService;
    }
    speedChange() {
        this.updateCab();
    }
    up() {
        this.cab.direction = 'UP';
        this.updateCab();
    }
    down() {
        this.cab.direction = 'DOWN';
        this.updateCab();
    }
    halt() {
        this.cab.speed = 0;
        this.updateCab();
    }
    stop() {
        if (this.cab.direction == 'UP') {
            this.cab.speed = 0;
            this.cab.direction = 'FSTOP';
        }
        else if (this.cab.direction == 'DOWN') {
            this.cab.speed = 0;
            this.cab.direction = 'RSTOP';
        }
        this.updateCab();
    }
    updateCab() {
        this.cabService.updateCab(this.cab);
    }
    updateCabFunction() {
        this.cabService.updateCabFunction(this.cab);
    }
    runMacro(macro) {
        this.macroService.runMacro(macro);
    }
    toggleFunction(cabFunction) {
        cabFunction.state = !cabFunction.state;
        this.updateCabFunction();
    }
    setCab(cab) {
        this.cab = cab;
    }
    getCab() {
        this.cabService.getCab().subscribe(cab => this.setCab(cab));
    }
    ngOnInit() {
        this.getCab();
    }
};
CabComponent = __decorate([
    core_1.Component({
        moduleId: module.id,
        templateUrl: '../../cab/cab.html',
        selector: 'dcc-cab'
    }),
    __metadata("design:paramtypes", [cab_service_1.CabService, macro_service_1.MacroService])
], CabComponent);
exports.CabComponent = CabComponent;
//# sourceMappingURL=cab.component.js.map