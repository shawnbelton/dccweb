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
 * Created by shawn on 28/02/17.
 */
var core_1 = require("@angular/core");
var accessoryDecoder_1 = require("../models/accessoryDecoder");
var accessoryDecoder_service_1 = require("../services/accessoryDecoder.service");
var decoderAccessoryType_1 = require("../models/decoderAccessoryType");
var accessoryOperation_1 = require("../models/accessoryOperation");
var AccessoryComponent = (function () {
    function AccessoryComponent(accessoryDecoderService) {
        this.accessoryDecoderService = accessoryDecoderService;
    }
    AccessoryComponent.prototype.newAccessory = function () {
        this.editAccessory = new accessoryDecoder_1.AccessoryDecoder();
        this.editAccessory.accessoryDecoderType = new decoderAccessoryType_1.DecoderAccessoryType();
    };
    AccessoryComponent.prototype.setCurrentAccessory = function (accessory) {
        if (accessory == null) {
            this.newAccessory();
        }
        else {
            this.editAccessory = accessory;
        }
    };
    AccessoryComponent.prototype.startEditAccessory = function (accessory) {
        var newAccessory = new accessoryDecoder_1.AccessoryDecoder();
        newAccessory.accessoryDecoderId = accessory.accessoryDecoderId;
        newAccessory.accessoryDecoderType = accessory.accessoryDecoderType;
        newAccessory.address = accessory.address;
        newAccessory.name = accessory.name;
        this.setCurrentAccessory(newAccessory);
    };
    AccessoryComponent.prototype.cancelEdit = function () {
        this.newAccessory();
    };
    AccessoryComponent.prototype.saveAccessory = function () {
        this.accessoryDecoderService.saveAccessory(this.editAccessory);
        this.newAccessory();
    };
    AccessoryComponent.prototype.operateAccessory = function (accessoryAddress, operationValue) {
        var accessoryOperation = new accessoryOperation_1.AccessoryOperation();
        accessoryOperation.accessoryAddress = accessoryAddress;
        accessoryOperation.operationValue = operationValue;
        this.accessoryDecoderService.operateAccessory(accessoryOperation);
    };
    AccessoryComponent.prototype.isState = function (accessory, accessoryOperation) {
        return accessory.currentValue == accessoryOperation.decoderOperationValue;
    };
    AccessoryComponent.prototype.getAccessories = function () {
        var _this = this;
        this.accessoryDecoderService
            .getAccessories().subscribe(function (data) { return _this.accessoryDecoders = data; });
        this.accessoryDecoderService
            .getAccessory().subscribe(function (accessory) { return _this.setCurrentAccessory(accessory); });
    };
    AccessoryComponent.prototype.getAccessoryTypes = function () {
        var _this = this;
        this.accessoryDecoderService
            .getAccessoryTypes().subscribe(function (data) { return _this.accessoryDecoderTypes = data; });
    };
    AccessoryComponent.prototype.ngOnInit = function () {
        this.getAccessories();
        this.getAccessoryTypes();
    };
    AccessoryComponent = __decorate([
        core_1.Component({
            moduleId: module.id,
            templateUrl: '../../accessory/accessories.html'
        }), 
        __metadata('design:paramtypes', [accessoryDecoder_service_1.AccessoryDecoderService])
    ], AccessoryComponent);
    return AccessoryComponent;
}());
exports.AccessoryComponent = AccessoryComponent;
//# sourceMappingURL=accessory.component.js.map