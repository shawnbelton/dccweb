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
var decoder_service_1 = require("../services/decoder.service");
var macro_service_1 = require("../services/macro.service");
var decoderFunction_1 = require("../models/decoderFunction");
var linked_macro_1 = require("../models/linked.macro");
var DecoderComponent = (function () {
    function DecoderComponent(decoderService, macroService) {
        this.decoderService = decoderService;
        this.macroService = macroService;
        this.decoderTabState = "FUNCTIONS";
    }
    DecoderComponent.prototype.setFunctionsTab = function () {
        this.decoderTabState = "FUNCTIONS";
        return false;
    };
    DecoderComponent.prototype.setMacrosTab = function () {
        this.decoderTabState = "MACROS";
        return false;
    };
    DecoderComponent.prototype.isEmptyDecoder = function () {
        return this.currentDecoder != null && this.currentDecoder.currentAddress != null;
    };
    DecoderComponent.prototype.isFunctionsTab = function () {
        return this.isEmptyDecoder() && this.decoderTabState == "FUNCTIONS";
    };
    DecoderComponent.prototype.isMacrosTab = function () {
        return this.isEmptyDecoder() && this.decoderTabState == "MACROS";
    };
    DecoderComponent.prototype.readDecoder = function () {
        this.decoderService.readDecoder();
    };
    DecoderComponent.prototype.editDecoder = function (decoder) {
        this.decoderService.fetchDecoder(decoder.decoderId);
    };
    DecoderComponent.prototype.getDecoders = function () {
        var _this = this;
        this.decoderService
            .getDecoders().subscribe(function (decoders) { return _this.decoders = decoders; });
        this.decoderService
            .getDecoder().subscribe(function (decoder) { return _this.setCurrentDecoder(decoder); });
    };
    DecoderComponent.prototype.getMacros = function () {
        var _this = this;
        this.macroService.getMacros().subscribe(function (macros) { return _this.macros = macros; });
    };
    DecoderComponent.prototype.addDecoderFunction = function () {
        this.newDecoderFunction.decoderId = this.currentDecoder.decoderId;
        this.decoderService.addDecoderFunction(this.newDecoderFunction);
    };
    DecoderComponent.prototype.linkMacro = function () {
        this.linkedMacro.decoderId = this.currentDecoder.decoderId;
        for (var _i = 0, _a = this.macros; _i < _a.length; _i++) {
            var macro = _a[_i];
            if (macro.macroId == this.linkedMacro.macroId) {
                this.linkedMacro.macro = macro;
            }
        }
        this.decoderService.linkMacro(this.linkedMacro);
    };
    DecoderComponent.prototype.deleteDecoderFunction = function (decoderFunction) {
        this.decoderService.deleteDecoderFunction(decoderFunction);
    };
    DecoderComponent.prototype.unlinkMacro = function (linkedMacro) {
        this.decoderService.unlinkMacro(linkedMacro);
    };
    DecoderComponent.prototype.setCurrentDecoder = function (decoder) {
        this.currentDecoder = decoder;
        this.availableFunctions = new Array();
        for (var index = 0; index < 29; index++) {
            if (!this.hasFunction(index)) {
                this.availableFunctions.push(index);
            }
        }
        this.newDecoderFunction = new decoderFunction_1.DecoderFunction();
        this.linkedMacro = new linked_macro_1.LinkedMacro();
        if (this.availableFunctions.length > 0) {
            this.newDecoderFunction.number = this.availableFunctions[0];
        }
    };
    DecoderComponent.prototype.hasFunction = function (index) {
        var found = false;
        if (this.currentDecoder != null && this.currentDecoder.decoderFunctions != null) {
            for (var _i = 0, _a = this.currentDecoder.decoderFunctions; _i < _a.length; _i++) {
                var decoderFunction = _a[_i];
                if (decoderFunction.number == index) {
                    found = true;
                }
            }
        }
        return found;
    };
    DecoderComponent.prototype.ngOnInit = function () {
        this.getDecoders();
        this.getMacros();
    };
    DecoderComponent = __decorate([
        core_1.Component({
            moduleId: module.id,
            templateUrl: '../../decoder/decoder.html'
        }), 
        __metadata('design:paramtypes', [decoder_service_1.DecoderService, macro_service_1.MacroService])
    ], DecoderComponent);
    return DecoderComponent;
}());
exports.DecoderComponent = DecoderComponent;
//# sourceMappingURL=decoder.component.js.map