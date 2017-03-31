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
 * Created by shawn on 25/11/16.
 */
var core_1 = require("@angular/core");
var settings_service_1 = require("../services/settings.service");
var SettingsComponent = (function () {
    function SettingsComponent(settingsService) {
        this.settingsService = settingsService;
    }
    SettingsComponent.prototype.getSettings = function () {
        var _this = this;
        this.settingsService.getSettings().subscribe(function (settings) { return _this.setSettings(settings); });
    };
    SettingsComponent.prototype.getInterfaces = function () {
        var _this = this;
        this.settingsService.getInterfaces().subscribe(function (interfaces) { return _this.setInterfaces(interfaces); });
    };
    SettingsComponent.prototype.saveSettings = function () {
        this.settingsService.saveSettings(this.settings);
    };
    SettingsComponent.prototype.setSettings = function (settings) {
        this.settings = settings;
    };
    SettingsComponent.prototype.setInterfaces = function (interfaces) {
        this.interfaces = interfaces;
    };
    SettingsComponent.prototype.ngOnInit = function () {
        this.getSettings();
        this.getInterfaces();
    };
    SettingsComponent = __decorate([
        core_1.Component({
            moduleId: module.id,
            selector: 'dcc-settings',
            templateUrl: '../../settings/settings.html'
        }), 
        __metadata('design:paramtypes', [settings_service_1.SettingsService])
    ], SettingsComponent);
    return SettingsComponent;
}());
exports.SettingsComponent = SettingsComponent;
//# sourceMappingURL=settings.component.js.map