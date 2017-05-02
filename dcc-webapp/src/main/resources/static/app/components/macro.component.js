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
 * Created by shawn on 28/11/16.
 */
var core_1 = require("@angular/core");
var macro_1 = require("../models/macro");
var macro_step_1 = require("../models/macro.step");
var train_service_1 = require("../services/train.service");
var macro_service_1 = require("../services/macro.service");
var accessoryDecoder_service_1 = require("../services/accessoryDecoder.service");
var MacroComponent = (function () {
    function MacroComponent(trainService, accessoryService, macroService) {
        this.trainService = trainService;
        this.accessoryService = accessoryService;
        this.macroService = macroService;
    }
    MacroComponent.prototype.getAccessories = function () {
        var _this = this;
        this.accessoryService.getAccessories().subscribe(function (accessories) { return _this.accessories = accessories; });
    };
    MacroComponent.prototype.getTrains = function () {
        var _this = this;
        this.trainService.getTrains().subscribe(function (trains) { return _this.trains = trains; });
    };
    MacroComponent.prototype.getMacros = function () {
        var _this = this;
        this.macroService.getMacros().subscribe(function (macros) { return _this.macros = macros; });
    };
    MacroComponent.prototype.setMacro = function (macro) {
        this.macro = macro;
        this.macro.steps = this.macro.steps.sort(function (step1, step2) { return step1.number - step2.number; });
    };
    MacroComponent.prototype.getMacro = function () {
        var _this = this;
        this.macroService.getMacro().subscribe(function (macro) { return _this.setMacro(macro); });
    };
    MacroComponent.prototype.editMacro = function (macro) {
        this.macroService.editMacro(macro);
    };
    MacroComponent.prototype.saveMacro = function () {
        this.macroService.saveMacro(this.macro);
        this.macro = new macro_1.Macro();
    };
    MacroComponent.prototype.deleteMacro = function (macro) {
        this.macroService.deleteMacro(macro);
        this.macro = new macro_1.Macro();
    };
    MacroComponent.prototype.runMacro = function (macro) {
        this.macroService.runMacro(macro);
    };
    MacroComponent.prototype.macroValid = function () {
        return this.macro.name != null && this.macro.name.length > 0 && this.macro.steps.length > 0;
    };
    MacroComponent.prototype.saveMacroName = function () {
        this.macro.editing = false;
    };
    MacroComponent.prototype.editMacroName = function () {
        this.macro.editing = true;
    };
    MacroComponent.prototype.addStep = function () {
        var macroStep = new macro_step_1.MacroStep();
        macroStep.number = this.macro.steps.length + 1;
        macroStep.editing = true;
        this.macro.steps.push(macroStep);
    };
    MacroComponent.prototype.saveStep = function (step) {
        step.editing = false;
    };
    MacroComponent.prototype.editStep = function (step) {
        step.editing = true;
    };
    MacroComponent.prototype.fetchAccessory = function (step) {
        var stepAccessory;
        for (var _i = 0, _a = this.accessories; _i < _a.length; _i++) {
            var accessory = _a[_i];
            if (accessory.accessoryDecoderId == step.targetId) {
                stepAccessory = accessory;
            }
        }
        return stepAccessory;
    };
    MacroComponent.prototype.fetchTrain = function (step) {
        var stepTrain;
        for (var _i = 0, _a = this.trains; _i < _a.length; _i++) {
            var train = _a[_i];
            if (train.trainId == step.targetId) {
                stepTrain = train;
            }
        }
        return stepTrain;
    };
    MacroComponent.prototype.fetchDecoder = function (step) {
        return this.fetchTrain(step).decoder;
    };
    MacroComponent.prototype.isFirst = function (step) {
        return step.number == 1;
    };
    MacroComponent.prototype.isLast = function (step) {
        return step.number == this.macro.steps.length;
    };
    MacroComponent.prototype.moveUp = function (step) {
        var stepNumber = step.number - 1;
        var prev = this.macro.steps[stepNumber - 1];
        this.macro.steps[stepNumber - 1] = step;
        this.macro.steps[stepNumber] = prev;
        this.renumber();
        return false;
    };
    MacroComponent.prototype.moveDown = function (step) {
        var stepNumber = step.number - 1;
        var next = this.macro.steps[stepNumber + 1];
        this.macro.steps[stepNumber + 1] = step;
        this.macro.steps[stepNumber] = next;
        this.renumber();
        return false;
    };
    MacroComponent.prototype.removeStep = function (step) {
        var stepNumber = step.number - 1;
        this.macro.steps.splice(stepNumber, 1);
        this.renumber();
        return false;
    };
    MacroComponent.prototype.renumber = function () {
        var index = 1;
        for (var _i = 0, _a = this.macro.steps; _i < _a.length; _i++) {
            var step = _a[_i];
            step.number = index++;
        }
    };
    MacroComponent.prototype.isTrainFunction = function (step) {
        return step.type == 'decoderFunction' || step.type == 'setSpeed';
    };
    MacroComponent.prototype.isAccessoryFunction = function (step) {
        return step.type == 'setAccessory';
    };
    MacroComponent.prototype.displayStep = function (step) {
        var display = step.number.toString() + " ";
        switch (step.type) {
            case "delay":
                display = display + "Wait " + step.delay.toString() + " Seconds";
                break;
            case "decoderFunction":
                display = display + this.displayFunction(step);
                break;
            case "setSpeed":
                display = display + this.displaySetSpeed(step);
                break;
            case "setAccessory":
                display = display + this.displaySetAccessory(step);
                break;
        }
        return display;
    };
    MacroComponent.prototype.displayFunction = function (step) {
        var train = this.fetchTrain(step);
        var display = "Turn " + this.getState(step.functionStatus);
        display = display + " " + this.displayFunctionName(train, step.functionNumber);
        display = display + " on " + this.displayTrainInfo(train);
        return display;
    };
    MacroComponent.prototype.displaySetSpeed = function (step) {
        var train = this.fetchTrain(step);
        var display = "Set speed to " + step.value;
        display = display + " on " + this.displayTrainInfo(train);
        return display;
    };
    MacroComponent.prototype.displaySetAccessory = function (step) {
        var accessory = this.fetchAccessory(step);
        var display = "Set Accessory " + accessory.name;
        display = display + " to " + this.displayAccessoryOperation(step, accessory);
        return display;
    };
    MacroComponent.prototype.displayTrainInfo = function (train) {
        return train.number + " " + train.name;
    };
    MacroComponent.prototype.displayFunctionName = function (train, functionNumber) {
        var decoderFunction;
        for (var _i = 0, _a = train.decoder.decoderFunctions; _i < _a.length; _i++) {
            var iFunction = _a[_i];
            if (iFunction.number == functionNumber) {
                decoderFunction = iFunction;
            }
        }
        return decoderFunction.name;
    };
    MacroComponent.prototype.getState = function (functionStatus) {
        var value;
        if (functionStatus == "true") {
            value = "On";
        }
        else {
            value = "Off";
        }
        return value;
    };
    MacroComponent.prototype.displayAccessoryOperation = function (step, accessory) {
        var accessoryOperation = "";
        for (var _i = 0, _a = accessory.accessoryDecoderType.decoderTypeOperations; _i < _a.length; _i++) {
            var operation = _a[_i];
            if (operation.decoderOperationValue == step.value) {
                accessoryOperation = operation.decoderTypeOperation;
            }
        }
        return accessoryOperation;
    };
    MacroComponent.prototype.accessoryOperations = function (step) {
        var accessory = this.fetchAccessory(step);
        return accessory.accessoryDecoderType.decoderTypeOperations;
    };
    MacroComponent.prototype.ngOnInit = function () {
        this.macro = new macro_1.Macro();
        this.getMacro();
        this.getMacros();
        this.getTrains();
        this.getAccessories();
    };
    MacroComponent = __decorate([
        core_1.Component({
            moduleId: module.id,
            templateUrl: '../../macroedit/macro.html'
        }), 
        __metadata('design:paramtypes', [train_service_1.TrainService, accessoryDecoder_service_1.AccessoryDecoderService, macro_service_1.MacroService])
    ], MacroComponent);
    return MacroComponent;
}());
exports.MacroComponent = MacroComponent;
//# sourceMappingURL=macro.component.js.map