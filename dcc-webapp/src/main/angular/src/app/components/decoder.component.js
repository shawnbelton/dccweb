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
 * Created by shawn on 16/11/16.
 */
const core_1 = require("@angular/core");
const decoder_service_1 = require("../services/decoder.service");
const macro_service_1 = require("../services/macro.service");
const decoderFunction_1 = require("../models/decoderFunction");
const linked_macro_1 = require("../models/linked.macro");
let DecoderComponent = class DecoderComponent {
    constructor(decoderService, macroService) {
        this.decoderService = decoderService;
        this.macroService = macroService;
        this.decoderTabState = "FUNCTIONS";
    }
    setFunctionsTab() {
        this.decoderTabState = "FUNCTIONS";
        return false;
    }
    setMacrosTab() {
        this.decoderTabState = "MACROS";
        return false;
    }
    isEmptyDecoder() {
        return this.currentDecoder != null && this.currentDecoder.currentAddress != null;
    }
    isFunctionsTab() {
        return this.isEmptyDecoder() && this.decoderTabState == "FUNCTIONS";
    }
    isMacrosTab() {
        return this.isEmptyDecoder() && this.decoderTabState == "MACROS";
    }
    readDecoder() {
        this.decoderService.readDecoder();
    }
    editDecoder(decoder) {
        this.decoderService.fetchDecoder(decoder.decoderId);
    }
    getDecoders() {
        this.decoderService
            .getDecoders().subscribe(decoders => this.decoders = decoders);
        this.decoderService
            .getDecoder().subscribe(decoder => this.setCurrentDecoder(decoder));
    }
    getMacros() {
        this.macroService.getMacros().subscribe(macros => this.macros = macros);
    }
    addDecoderFunction() {
        this.newDecoderFunction.decoderId = this.currentDecoder.decoderId;
        this.decoderService.addDecoderFunction(this.newDecoderFunction);
    }
    linkMacro() {
        this.linkedMacro.decoderId = this.currentDecoder.decoderId;
        for (let macro of this.macros) {
            if (macro.macroId == this.linkedMacro.macroId) {
                this.linkedMacro.macro = macro;
            }
        }
        this.decoderService.linkMacro(this.linkedMacro);
    }
    deleteDecoderFunction(decoderFunction) {
        this.decoderService.deleteDecoderFunction(decoderFunction);
    }
    unlinkMacro(linkedMacro) {
        this.decoderService.unlinkMacro(linkedMacro);
    }
    setCurrentDecoder(decoder) {
        this.currentDecoder = decoder;
        this.availableFunctions = new Array();
        for (let index = 0; index < 29; index++) {
            if (!this.hasFunction(index)) {
                this.availableFunctions.push(index);
            }
        }
        this.newDecoderFunction = new decoderFunction_1.DecoderFunction();
        this.linkedMacro = new linked_macro_1.LinkedMacro();
        if (this.availableFunctions.length > 0) {
            this.newDecoderFunction.number = this.availableFunctions[0];
        }
    }
    hasFunction(index) {
        let found = false;
        if (this.currentDecoder != null && this.currentDecoder.decoderFunctions != null) {
            for (let decoderFunction of this.currentDecoder.decoderFunctions) {
                if (decoderFunction.number == index) {
                    found = true;
                }
            }
        }
        return found;
    }
    ngOnInit() {
        this.getDecoders();
        this.getMacros();
    }
};
DecoderComponent = __decorate([
    core_1.Component({
        moduleId: module.id,
        templateUrl: '../../decoder/decoder.html'
    }),
    __metadata("design:paramtypes", [decoder_service_1.DecoderService, macro_service_1.MacroService])
], DecoderComponent);
exports.DecoderComponent = DecoderComponent;
//# sourceMappingURL=decoder.component.js.map