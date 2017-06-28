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
 * Created by shawn on 28/02/17.
 */
const core_1 = require("@angular/core");
const accessoryDecoder_1 = require("../models/accessoryDecoder");
const accessoryDecoder_service_1 = require("../services/accessoryDecoder.service");
const decoderAccessoryType_1 = require("../models/decoderAccessoryType");
const accessoryOperation_1 = require("../models/accessoryOperation");
const macro_1 = require("../models/macro");
const macro_service_1 = require("../services/macro.service");
let AccessoryComponent = class AccessoryComponent {
    constructor(accessoryDecoderService, macroService) {
        this.accessoryDecoderService = accessoryDecoderService;
        this.macroService = macroService;
    }
    newAccessory() {
        this.editAccessory = new accessoryDecoder_1.AccessoryDecoder();
        this.editAccessory.macro = new macro_1.Macro();
        this.editAccessory.accessoryDecoderType = new decoderAccessoryType_1.DecoderAccessoryType();
    }
    setCurrentAccessory(accessory) {
        if (accessory == null) {
            this.newAccessory();
        }
        else {
            this.editAccessory = accessory;
        }
    }
    startEditAccessory(accessory) {
        let newAccessory = new accessoryDecoder_1.AccessoryDecoder();
        newAccessory.accessoryDecoderId = accessory.accessoryDecoderId;
        newAccessory.accessoryDecoderType = accessory.accessoryDecoderType;
        newAccessory.address = accessory.address;
        newAccessory.name = accessory.name;
        newAccessory.currentValue = accessory.currentValue;
        newAccessory.macro = accessory.macro;
        if (newAccessory.macro == null) {
            newAccessory.macro = new macro_1.Macro();
        }
        this.setCurrentAccessory(newAccessory);
    }
    cancelEdit() {
        this.newAccessory();
    }
    saveAccessory() {
        if (this.editAccessory.macro.macroId == null || this.editAccessory.macro.macroId.toString() == "") {
            this.editAccessory.macro = null;
        }
        this.accessoryDecoderService.saveAccessory(this.editAccessory);
        this.newAccessory();
    }
    operateAccessory(accessoryAddress, operationValue) {
        let accessoryOperation = new accessoryOperation_1.AccessoryOperation();
        accessoryOperation.accessoryAddress = accessoryAddress;
        accessoryOperation.operationValue = operationValue;
        this.accessoryDecoderService.operateAccessory(accessoryOperation);
    }
    isState(accessory, accessoryOperation) {
        return accessory.currentValue == accessoryOperation.decoderOperationValue;
    }
    getAccessories() {
        this.accessoryDecoderService
            .getAccessories().subscribe(data => this.accessoryDecoders = data);
        this.accessoryDecoderService
            .getAccessory().subscribe(accessory => this.setCurrentAccessory(accessory));
    }
    getAccessoryTypes() {
        this.accessoryDecoderService
            .getAccessoryTypes().subscribe(data => this.accessoryDecoderTypes = data);
    }
    getMacros() {
        this.macroService.getMacros().subscribe(macros => this.setMacros(macros));
    }
    setMacros(macros) {
        this.macros = macros;
    }
    ngOnInit() {
        this.getMacros();
        this.getAccessories();
        this.getAccessoryTypes();
    }
};
AccessoryComponent = __decorate([
    core_1.Component({
        moduleId: module.id,
        templateUrl: '../../accessory/accessories.html'
    }),
    __metadata("design:paramtypes", [accessoryDecoder_service_1.AccessoryDecoderService, macro_service_1.MacroService])
], AccessoryComponent);
exports.AccessoryComponent = AccessoryComponent;
//# sourceMappingURL=accessory.component.js.map