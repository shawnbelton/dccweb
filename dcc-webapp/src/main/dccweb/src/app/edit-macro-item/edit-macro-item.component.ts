import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Macro} from '../models/macro';
import {Loco} from '../models/loco';
import {AccessoryDecoder} from '../models/accessory-decoder';
import {Block} from '../models/block';
import {RelayController} from '../models/relay-controller';
import {LocoService} from '../loco.service';
import {BlockService} from '../block.service';
import {AccessoryDecoderService} from '../accessory-decoder.service';
import {RelayService} from '../relay.service';
import {MacroService} from '../macro.service';
import {MacroStep} from '../models/macro-step';
import {Decoder} from '../models/decoder';
import {DecoderFunction} from '../models/decoder-function';
import {DecoderAccessoryTypeOperation} from '../models/decoder-accessory-type-operation';

@Component({
  selector: 'app-edit-macro-item',
  templateUrl: './edit-macro-item.component.html',
  styleUrls: ['./edit-macro-item.component.css']
})
export class EditMacroItemComponent implements OnInit {

  @Input() macro: Macro;
  @Output() saved = new EventEmitter<string>();
  locos: Loco[];
  accessories: AccessoryDecoder[];
  blocks: Block[];
  relayControllers: RelayController[];

  constructor(private locoService: LocoService, private blockService: BlockService,
              private accessoryService: AccessoryDecoderService,
              private relayService: RelayService, private macroService: MacroService) {
  }

  getAccessories(): void {
    this.accessoryService.getAccessories().subscribe(accessories => this.accessories = accessories);
  }

  getLocos(): void {
    this.locoService.getLocos().subscribe(locos => this.locos = locos);
  }

  getBlocks(): void {
    this.blockService.getBlocks().subscribe(blocks => this.blocks = blocks);
  }

  getRelayControllers(): void {
    this.relayService.getRelayControllers().subscribe(relayControllers => this.relayControllers = relayControllers);
  }

  saveMacro(): void {
    this.macroService.saveMacro(this.macro);
    this.saved.emit('saved');
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
    const macroStep: MacroStep = new MacroStep();
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
    for (const accessory of this.accessories) {
      if (accessory.accessoryDecoderId === step.targetId) {
        stepAccessory = accessory;
      }
    }
    return stepAccessory;
  }

  fetchLoco(step: MacroStep): Loco {
    let stepLoco: Loco;
    for (const loco of this.locos) {
      if (loco.locoId === step.targetId) {
        stepLoco = loco;
      }
    }
    return stepLoco;
  }

  fetchDecoder(step: MacroStep): Decoder {
    return this.fetchLoco(step).decoder;
  }

  isFirst(step: MacroStep): boolean {
    return step.number === 1;
  }

  isLast(step: MacroStep): boolean {
    return step.number === this.macro.steps.length;
  }

  moveUp(step: MacroStep): boolean {
    const stepNumber: number = step.number - 1;
    const prev: MacroStep = this.macro.steps[stepNumber - 1];
    this.macro.steps[stepNumber - 1] = step;
    this.macro.steps[stepNumber] = prev;
    this.renumber();
    return false;
  }

  moveDown(step: MacroStep): boolean {
    const stepNumber: number = step.number - 1;
    const next: MacroStep = this.macro.steps[stepNumber + 1];
    this.macro.steps[stepNumber + 1] = step;
    this.macro.steps[stepNumber] = next;
    this.renumber();
    return false;
  }

  removeStep(step: MacroStep): boolean {
    const stepNumber: number = step.number - 1;
    this.macro.steps.splice(stepNumber, 1);
    this.renumber();
    return false;
  }

  renumber(): void {
    let index = 1;
    for (const step of this.macro.steps) {
      step.number = index++;
    }
  }

  isLocoFunction(step: MacroStep): boolean {
    return step.type === 'decoderFunction' || step.type === 'setSpeed';
  }

  isAccessoryFunction(step: MacroStep): boolean {
    return step.type === 'setAccessory' || step.type === 'isAccessory';
  }

  displayStep(step: MacroStep): string {
    let display: string = step.number.toString() + ' ';
    switch (step.type) {
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
    const loco: Loco = this.fetchLoco(step);
    let display: string = 'Turn ' + this.getState(step.functionStatus);
    display = display + ' ' + this.displayFunctionName(loco, step.functionNumber);
    display = display + ' on ' + this.displayLocoInfo(loco);
    return display;
  }

  displaySetSpeed(step: MacroStep): string {
    const loco: Loco = this.fetchLoco(step);
    let display: string = 'Set speed to ' + step.value;
    display = display + ' on ' + this.displayLocoInfo(loco);
    return display;
  }

  displaySetAccessory(step: MacroStep): string {
    const accessory: AccessoryDecoder = this.fetchAccessory(step);
    let display: string = 'Set Accessory ' + accessory.name;
    display = display + ' to ' + this.displayAccessoryOperation(step, accessory);
    return display;
  }

  displayIsAccessory(step: MacroStep): string {
    const accessory: AccessoryDecoder = this.fetchAccessory(step);
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
    for (const iFunction of loco.decoder.decoderFunctions) {
      if (iFunction.number === functionNumber) {
        decoderFunction = iFunction;
      }
    }
    return decoderFunction.name;
  }

  getState(functionStatus: string): String {
    let value: string;
    if (functionStatus === 'true') {
      value = 'On';
    } else {
      value = 'Off';
    }
    return value;
  }

  displayAccessoryOperation(step: MacroStep, accessory: AccessoryDecoder): string {
    let accessoryOperation = '';
    for (const operation of accessory.accessoryDecoderType.decoderTypeOperations) {
      if (operation.decoderOperationValue === step.value) {
        accessoryOperation = operation.decoderTypeOperation;
      }
    }
    return accessoryOperation;
  }

  accessoryOperations(step: MacroStep): DecoderAccessoryTypeOperation[] {
    const accessory: AccessoryDecoder = this.fetchAccessory(step);
    return accessory.accessoryDecoderType.decoderTypeOperations;
  }

  displayIsBlock(step: MacroStep): string {
    let state: string;
    let display: string = 'Is Block ' + this.getBlock(step);
    switch (step.value) {
      case 0:
        state = 'unoccupied.';
        break;
      case 1:
        state = 'occupied.';
        break;
    }
    display = display + ' ' + state;
    return display;
  }

  displaySetRelay(step: MacroStep): string {
    let display: string = 'Set Relay ' + step.functionNumber + ' of ' + this.getRelay(step) + ' to ';
    if (0 === step.value) {
      display = display + 'off';
    } else {
      display = display + 'on';
    }
    return display;
  }

  getRelay(step: MacroStep): string {
    let relayName: string;
    for (const relayController of this.relayControllers) {
      if (relayController.controllerId === step.blockId) {
        relayName = relayController.controllerName;
      }
    }
    return relayName;
  }

  getBlock(step: MacroStep): string {
    let blockName: string;
    for (const block of this.blocks) {
      if (block.blockId === step.blockId) {
        blockName = block.blockName;
      }
    }
    return blockName;
  }

  ngOnInit() {
    this.getLocos();
    this.getAccessories();
    this.getRelayControllers();
    this.getBlocks();
  }

}
