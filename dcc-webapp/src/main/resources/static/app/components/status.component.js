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
 * Created by shawn on 16/11/16.
 */
var core_1 = require("@angular/core");
var status_1 = require("../models/status");
var status_service_1 = require("../services/status.service");
var StatusComponent = (function () {
    function StatusComponent(statusService) {
        this.statusService = statusService;
    }
    StatusComponent.prototype.getStatus = function () {
        var _this = this;
        this.statusService.getStatus().subscribe(function (status) { return _this.status = status; });
    };
    StatusComponent.prototype.ngOnInit = function () {
        this.status = new status_1.Status();
        this.status.status = 'LOADING';
        this.getStatus();
    };
    StatusComponent = __decorate([
        core_1.Component({
            moduleId: module.id,
            selector: 'dcc-status',
            template: '<p class="navbar-text">Status: {{status.status}}</p>'
        }), 
        __metadata('design:paramtypes', [status_service_1.StatusService])
    ], StatusComponent);
    return StatusComponent;
}());
exports.StatusComponent = StatusComponent;
//# sourceMappingURL=status.component.js.map