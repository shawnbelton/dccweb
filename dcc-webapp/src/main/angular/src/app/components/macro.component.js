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
 * Created by shawn on 28/11/16.
 */
const core_1 = require("@angular/core");
const macro_1 = require("../models/macro");
const macro_step_1 = require("../models/macro.step");
const train_service_1 = require("../services/train.service");
const macro_service_1 = require("../services/macro.service");
const accessoryDecoder_service_1 = require("../services/accessoryDecoder.service");
const block_service_1 = require("../services/block.service");
const relay_service_1 = require("../services/relay.service");
let MacroComponent = class MacroComponent {
    constructor(trainService, blockService, accessoryService, relayService, macroService) {
        this.trainService = trainService;
        this.blockService = blockService;
        this.accessoryService = accessoryService;
        this.relayService = relayService;
        this.macroService = macroService;
    }
    getAccessories() {
        this.accessoryService.getAccessories().subscribe(accessories => this.accessories = accessories);
    }
    getTrains() {
        this.trainService.getTrains().subscribe(trains => this.trains = trains);
    }
    getBlocks() {
        this.blockService.getBlocks().subscribe(blocks => this.blocks = blocks);
    }
    getRelayContollers() {
        this.relayService.getRelayControllers().subscribe(relayControllers => this.relayControllers = relayControllers);
    }
    getMacros() {
        this.macroService.getMacros().subscribe(macros => this.macros = macros);
    }
    setMacro(macro) {
        this.macro = macro;
        this.macro.steps = this.macro.steps.sort((step1, step2) => step1.number - step2.number);
    }
    getMacro() {
        this.macroService.getMacro().subscribe(macro => this.setMacro(macro));
    }
    editMacro(macro) {
        this.macroService.editMacro(macro);
    }
    saveMacro() {
        this.macroService.saveMacro(this.macro);
        this.macro = new macro_1.Macro();
    }
    deleteMacro(macro) {
        this.macroService.deleteMacro(macro);
        this.macro = new macro_1.Macro();
    }
    runMacro(macro) {
        this.macroService.runMacro(macro);
    }
    macroValid() {
        return this.macro.name != null && this.macro.name.length > 0 && this.macro.steps.length > 0;
    }
    saveMacroName() {
        this.macro.editing = false;
    }
    editMacroName() {
        this.macro.editing = true;
    }
    addStep() {
        let macroStep = new macro_step_1.MacroStep();
        macroStep.number = this.macro.steps.length + 1;
        macroStep.editing = true;
        this.macro.steps.push(macroStep);
    }
    saveStep(step) {
        step.editing = false;
    }
    editStep(step) {
        step.editing = true;
    }
    fetchAccessory(step) {
        let stepAccessory;
        for (let accessory of this.accessories) {
            if (accessory.accessoryDecoderId == step.targetId) {
                stepAccessory = accessory;
            }
        }
        return stepAccessory;
    }
    fetchTrain(step) {
        let stepTrain;
        for (let train of this.trains) {
            if (train.trainId == step.targetId) {
                stepTrain = train;
            }
        }
        return stepTrain;
    }
    fetchDecoder(step) {
        return this.fetchTrain(step).decoder;
    }
    isFirst(step) {
        return step.number == 1;
    }
    isLast(step) {
        return step.number == this.macro.steps.length;
    }
    moveUp(step) {
        let stepNumber = step.number - 1;
        let prev = this.macro.steps[stepNumber - 1];
        this.macro.steps[stepNumber - 1] = step;
        this.macro.steps[stepNumber] = prev;
        this.renumber();
        return false;
    }
    moveDown(step) {
        let stepNumber = step.number - 1;
        let next = this.macro.steps[stepNumber + 1];
        this.macro.steps[stepNumber + 1] = step;
        this.macro.steps[stepNumber] = next;
        this.renumber();
        return false;
    }
    removeStep(step) {
        let stepNumber = step.number - 1;
        this.macro.steps.splice(stepNumber, 1);
        this.renumber();
        return false;
    }
    renumber() {
        let index = 1;
        for (let step of this.macro.steps) {
            step.number = index++;
        }
    }
    isTrainFunction(step) {
        return step.type == 'decoderFunction' || step.type == 'setSpeed';
    }
    isAccessoryFunction(step) {
        return step.type == 'setAccessory' || step.type == 'isAccessory';
    }
    displayStep(step) {
        let display = step.number.toString() + " ";
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
            case "exitMacro":
                display = display + "Exit Macro";
                break;
            case "thenGoTo":
                display = display + "Then goto step " + step.value.toString();
                break;
            case "isBlock":
                display = display + this.displayIsBlock(step);
                break;
            case "isAccessory":
                display = display + this.displayIsAccessory(step);
                break;
            case "setRelay":
                display = display + this.displaySetRelay(step);
                break;
        }
        return display;
    }
    displayFunction(step) {
        let train = this.fetchTrain(step);
        let display = "Turn " + this.getState(step.functionStatus);
        display = display + " " + this.displayFunctionName(train, step.functionNumber);
        display = display + " on " + this.displayTrainInfo(train);
        return display;
    }
    displaySetSpeed(step) {
        let train = this.fetchTrain(step);
        let display = "Set speed to " + step.value;
        display = display + " on " + this.displayTrainInfo(train);
        return display;
    }
    displaySetAccessory(step) {
        let accessory = this.fetchAccessory(step);
        let display = "Set Accessory " + accessory.name;
        display = display + " to " + this.displayAccessoryOperation(step, accessory);
        return display;
    }
    displayIsAccessory(step) {
        let accessory = this.fetchAccessory(step);
        let display = "Is Accessory " + accessory.name;
        display = display + " to " + this.displayAccessoryOperation(step, accessory);
        return display;
    }
    displayTrainInfo(train) {
        return train.number + " " + train.name;
    }
    displayFunctionName(train, functionNumber) {
        let decoderFunction;
        for (let iFunction of train.decoder.decoderFunctions) {
            if (iFunction.number == functionNumber) {
                decoderFunction = iFunction;
            }
        }
        return decoderFunction.name;
    }
    getState(functionStatus) {
        let value;
        if (functionStatus == "true") {
            value = "On";
        }
        else {
            value = "Off";
        }
        return value;
    }
    displayAccessoryOperation(step, accessory) {
        let accessoryOperation = "";
        for (let operation of accessory.accessoryDecoderType.decoderTypeOperations) {
            if (operation.decoderOperationValue == step.value) {
                accessoryOperation = operation.decoderTypeOperation;
            }
        }
        return accessoryOperation;
    }
    accessoryOperations(step) {
        let accessory = this.fetchAccessory(step);
        return accessory.accessoryDecoderType.decoderTypeOperations;
    }
    displayIsBlock(step) {
        let state;
        let display = "Is Block " + this.getBlock(step);
        switch (step.value) {
            case 0:
                state = "unoccupied.";
                break;
            case 1:
                state = "occupued.";
                break;
        }
        display = display + " " + state;
        return display;
    }
    displaySetRelay(step) {
        let display = "Set Relay " + step.functionNumber + " of " + this.getRelay(step) + " to ";
        if (0 == step.value) {
            display = display + "off";
        }
        else {
            display = display + "on";
        }
        return display;
    }
    getRelay(step) {
        let relayName;
        for (let relayController of this.relayControllers) {
            if (relayController.controllerId == step.blockId) {
                relayName = relayController.controllerName;
            }
        }
        return relayName;
    }
    getBlock(step) {
        let blockName;
        for (let block of this.blocks) {
            if (block.blockId == step.blockId) {
                blockName = block.blockName;
            }
        }
        return blockName;
    }
    ngOnInit() {
        this.macro = new macro_1.Macro();
        this.getMacro();
        this.getMacros();
        this.getTrains();
        this.getAccessories();
        this.getRelayContollers();
        this.getBlocks();
    }
};
MacroComponent = __decorate([
    core_1.Component({
        moduleId: module.id,
        templateUrl: '../../macroedit/macro.html'
    }),
    __metadata("design:paramtypes", [train_service_1.TrainService, block_service_1.BlockService,
        accessoryDecoder_service_1.AccessoryDecoderService,
        relay_service_1.RelayService, macro_service_1.MacroService])
], MacroComponent);
exports.MacroComponent = MacroComponent;
//# sourceMappingURL=macro.component.js.map