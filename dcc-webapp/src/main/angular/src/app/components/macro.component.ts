/**
 * Created by shawn on 28/11/16.
 */
import {Component, OnInit} from '@angular/core';
import {Macro} from '../models/macro';
import {MacroStep} from '../models/macro.step';
import {Loco} from '../models/loco';
import {LocoService} from '../services/loco.service';
import {Decoder} from '../models/decoder';
import {DecoderFunction} from '../models/decoderFunction';
import {MacroService} from '../services/macro.service';
import {AccessoryDecoder} from '../models/accessoryDecoder';
import {AccessoryDecoderService} from '../services/accessoryDecoder.service';
import {DecoderAccessoryTypeOperation} from '../models/decoderAccessoryTypeOpertation';
import {BlockService} from '../services/block.service';
import {Block} from '../models/block';
import {RelayController} from '../models/relayController';
import {RelayService} from '../services/relay.service';

@Component({
    moduleId: module.id,
    templateUrl: './../html/macroedit/macro.html'
})
export class MacroComponent implements OnInit {

    macro: Macro;
    locos: Loco[];
    accessories: AccessoryDecoder[];
    blocks: Block[];
    relayControllers: RelayController[];
    macros: Macro[];

    constructor(private locoService: LocoService, private blockService: BlockService,
                private accessoryService: AccessoryDecoderService,
                private relayService: RelayService, private macroService: MacroService) {}

    getAccessories(): void {
        this.accessoryService.getAccessories().subscribe(accessories => this.accessories = accessories);
    }

    getLocos(): void {
        this.locoService.getLocos().subscribe(locos => this.locos = locos);
    }

    getBlocks(): void {
        this.blockService.getBlocks().subscribe(blocks => this.blocks = blocks);
    }

    getRelayContollers(): void {
        this.relayService.getRelayControllers().subscribe(relayControllers => this.relayControllers = relayControllers);
    }

    getMacros(): void {
        this.macroService.getMacros().subscribe(macros => this.macros = macros);
    }

    setMacro(macro: Macro): void {
        this.macro = macro;
        this.macro.steps = this.macro.steps.sort((step1, step2) => step1.number - step2.number);
    }

    getMacro(): void {
        this.macroService.getMacro().subscribe(macro => this.setMacro(macro));
    }

    editMacro(macro: Macro): void {
        this.macroService.editMacro(macro);
    }

    saveMacro(): void {
        this.macroService.saveMacro(this.macro);
        this.macro = new Macro();
    }

    deleteMacro(macro: Macro): void {
        this.macroService.deleteMacro(macro);
        this.macro = new Macro();
    }

    runMacro(macroId: number): void {
        this.macroService.runMacroById(macroId);
    }

    macroValid(): boolean {
        return this.macro.name != null && this.macro.name.length > 0 && this.macro.steps.length > 0;
    }

    saveMacroName(): void {
        this.macro.editing = false;
    }

    editMacroName(): void {
        this.macro.editing = true;
    }

    addStep(): void {
        let macroStep: MacroStep = new MacroStep();
        macroStep.number = this.macro.steps.length + 1;
        macroStep.editing = true;
        this.macro.steps.push(macroStep);
    }

    saveStep(step: MacroStep): void {
        step.editing = false;
    }

    editStep(step: MacroStep): void {
        step.editing = true;
    }

    fetchAccessory(step: MacroStep): AccessoryDecoder {
        let stepAccessory: AccessoryDecoder;
        for(let accessory of this.accessories) {
            if (accessory.accessoryDecoderId == step.targetId) {
                stepAccessory = accessory;
            }
        }
        return stepAccessory;
    }

    fetchLoco(step: MacroStep): Loco {
        let stepLoco: Loco;
        for(let loco of this.locos) {
            if (loco.locoId == step.targetId) {
                stepLoco = loco;
            }
        }
        return stepLoco;
    }

    fetchDecoder(step: MacroStep): Decoder {
        return this.fetchLoco(step).decoder;
    }

    isFirst(step: MacroStep): boolean {
        return step.number == 1;
    }

    isLast(step: MacroStep): boolean {
        return step.number == this.macro.steps.length;
    }

    moveUp(step: MacroStep): boolean {
        let stepNumber: number = step.number - 1;
        let prev: MacroStep = this.macro.steps[stepNumber - 1];
        this.macro.steps[stepNumber - 1] = step;
        this.macro.steps[stepNumber] = prev;
        this.renumber();
        return false;
    }

    moveDown(step: MacroStep): boolean {
        let stepNumber: number = step.number - 1;
        let next: MacroStep = this.macro.steps[stepNumber + 1];
        this.macro.steps[stepNumber + 1] = step;
        this.macro.steps[stepNumber] = next;
        this.renumber();
        return false;
    }

    removeStep(step: MacroStep): boolean {
        let stepNumber: number = step.number - 1;
        this.macro.steps.splice(stepNumber, 1);
        this.renumber();
        return false;
    }

    renumber(): void {
        let index: number = 1;
        for(let step of this.macro.steps) {
            step.number = index++;
        }
    }

    isLocoFunction(step: MacroStep): boolean {
        return step.type == 'decoderFunction' || step.type == 'setSpeed';
    }

    isAccessoryFunction(step: MacroStep): boolean {
        return step.type == 'setAccessory' || step.type == 'isAccessory';
    }

    displayStep(step: MacroStep): string {
        let display: string = step.number.toString() + ' ';
        switch(step.type) {
            case 'delay':
                display = display + 'Wait ' + step.delay.toString() + ' Seconds';
                break;
            case 'decoderFunction':
                display = display + this.displayFunction(step);
                break;
            case 'setSpeed':
                display = display + this.displaySetSpeed(step);
                break;
            case 'setAccessory':
                display = display + this.displaySetAccessory(step);
                break;
            case 'exitMacro':
                display = display + 'Exit Macro';
                break;
            case 'thenGoTo':
                display = display + 'Then goto step ' + step.value.toString();
                break;
            case 'isBlock':
                display = display + this.displayIsBlock(step);
                break;
            case 'isAccessory':
                display = display + this.displayIsAccessory(step);
                break;
            case 'setRelay':
                display = display + this.displaySetRelay(step);
                break;
        }
        return display;
    }

    displayFunction(step: MacroStep): string {
        let loco: Loco = this.fetchLoco(step);
        let display: string = 'Turn ' + this.getState(step.functionStatus);
        display = display + ' ' + this.displayFunctionName(loco, step.functionNumber);
        display = display + ' on ' + this.displayLocoInfo(loco);
        return display;
    }

    displaySetSpeed(step: MacroStep): string {
        let loco: Loco = this.fetchLoco(step);
        let display: string = 'Set speed to ' + step.value;
        display = display + ' on ' + this.displayLocoInfo(loco);
        return display;
    }

    displaySetAccessory(step: MacroStep): string {
        let accessory: AccessoryDecoder = this.fetchAccessory(step);
        let display: string = 'Set Accessory ' + accessory.name;
        display = display + ' to ' + this.displayAccessoryOperation(step, accessory);
        return display;
    }

    displayIsAccessory(step: MacroStep): string {
        let accessory: AccessoryDecoder = this.fetchAccessory(step);
        let display: string = 'Is Accessory ' + accessory.name;
        display = display + ' set to ' + this.displayAccessoryOperation(step, accessory);
        return display;
    }

    displayLocoInfo(loco: Loco): string {
      let locoInfo: string = loco.number;
      if (loco.name != null) {
        locoInfo += ' ' + loco.name;
      }
      return locoInfo;
    }

    displayFunctionName(loco: Loco, functionNumber: number): string {
        let decoderFunction: DecoderFunction;
        for(let iFunction of loco.decoder.decoderFunctions) {
            if (iFunction.number == functionNumber) {
                decoderFunction = iFunction;
            }
        }
        return decoderFunction.name;
    }

    getState(functionStatus: string): String {
        let value: string;
        if (functionStatus == 'true') {
            value = 'On';
        } else {
            value = 'Off';
        }
        return value;
    }

    displayAccessoryOperation(step: MacroStep, accessory: AccessoryDecoder): string {
        let accessoryOperation: string = '';
        for(let operation of accessory.accessoryDecoderType.decoderTypeOperations) {
            if (operation.decoderOperationValue == step.value) {
                accessoryOperation = operation.decoderTypeOperation;
            }
        }
        return accessoryOperation;
    }

    accessoryOperations(step: MacroStep): DecoderAccessoryTypeOperation[] {
        let accessory: AccessoryDecoder = this.fetchAccessory(step);
        return accessory.accessoryDecoderType.decoderTypeOperations;
    }

    displayIsBlock(step: MacroStep): string {
        let state: string;
        let display: string = 'Is Block ' + this.getBlock(step);
        switch(step.value) {
            case 0:
                state = 'unoccupied.';
                break;
            case 1:
                state = 'occupued.';
                break;
        }
        display = display + ' ' + state;
        return display;
    }

    displaySetRelay(step: MacroStep): string {
        let display: string = 'Set Relay ' + step.functionNumber + ' of ' + this.getRelay(step) + ' to ';
        if (0 == step.value) {
            display = display + 'off';
        } else {
            display = display + 'on';
        }
        return display;
    }

    getRelay(step: MacroStep): string {
        let relayName: string;
        for(let relayController of this.relayControllers) {
            if (relayController.controllerId == step.blockId) {
                relayName = relayController.controllerName;
            }
        }
        return relayName;
    }

    getBlock(step: MacroStep): string {
        let blockName: string;
        for(let block of this.blocks) {
            if (block.blockId == step.blockId) {
                blockName = block.blockName;
            }
        }
        return blockName;
    }

    ngOnInit(): void {
        this.macro = new Macro();
        this.getMacro();
        this.getMacros();
        this.getLocos();
        this.getAccessories();
        this.getRelayContollers();
        this.getBlocks();
    }
}
