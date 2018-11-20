import {Component, OnInit} from '@angular/core';
import {AccessoryDecoder} from '../models/accessory-decoder';
import {DecoderAccessoryType} from '../models/decoder-accessory-type';
import {Macro} from '../models/macro';
import {AccessoryDecoderService} from '../accessory-decoder.service';
import {MacroService} from '../macro.service';
import {AccessoryOperation} from '../models/accessory-operation';
import {DecoderAccessoryTypeOperation} from '../models/decoder-accessory-type-operation';

@Component({
  selector: 'app-accessory',
  templateUrl: './accessory.component.html',
  styleUrls: ['./accessory.component.css']
})
export class AccessoryComponent implements OnInit {

  accessoryDecoders: AccessoryDecoder[];
  accessoryDecoderTypes: DecoderAccessoryType[];
  macros: Macro[];
  editAccessory: AccessoryDecoder;

  constructor(private accessoryDecoderService: AccessoryDecoderService, private macroService: MacroService) {
  }

  newAccessory(): void {
    this.editAccessory = new AccessoryDecoder();
    this.editAccessory.accessoryDecoderType = new DecoderAccessoryType();
  }

  setCurrentAccessory(accessory: AccessoryDecoder): void {
    if (accessory == null) {
      this.newAccessory();
    } else {
      this.editAccessory = accessory;
    }
  }

  startEditAccessory(accessory: AccessoryDecoder): void {
    const newAccessory: AccessoryDecoder = new AccessoryDecoder();
    newAccessory.accessoryDecoderId = accessory.accessoryDecoderId;
    newAccessory.accessoryDecoderType = accessory.accessoryDecoderType;
    newAccessory.address = accessory.address;
    newAccessory.name = accessory.name;
    newAccessory.currentValue = accessory.currentValue;
    newAccessory.macroId = accessory.macroId;
    this.setCurrentAccessory(newAccessory);
  }

  cancelEdit(): void {
    this.newAccessory();
  }

  saveAccessory(): void {
    this.accessoryDecoderService.saveAccessory(this.editAccessory);
    this.newAccessory();
  }

  operateAccessory(accessory: AccessoryDecoder, operationValue: number): void {
    const accessoryOperation: AccessoryOperation = new AccessoryOperation();
    accessoryOperation.accessoryAddress = accessory.address;
    accessoryOperation.operationValue = operationValue;
    accessory.currentValue = operationValue;
    this.accessoryDecoderService.operateAccessory(accessoryOperation);
  }

  isState(accessory: AccessoryDecoder, accessoryOperation: DecoderAccessoryTypeOperation): boolean {
    return accessory.currentValue === accessoryOperation.decoderOperationValue;
  }

  getAccessories(): void {
    this.accessoryDecoderService
      .getAccessories().subscribe(data => this.setAccessories(data));
    this.accessoryDecoderService
      .getAccessory().subscribe(accessory => this.setCurrentAccessory(accessory));
  }

  setAccessories(accessoryDecoders: AccessoryDecoder[]): void {
    this.accessoryDecoders = accessoryDecoders;
  }

  getAccessoryTypes(): void {
    this.accessoryDecoderService
      .getAccessoryTypes().subscribe(data => this.accessoryDecoderTypes = data);
  }

  getMacros(): void {
    this.macroService.getMacros().subscribe(macros => this.setMacros(macros));
  }

  setMacros(macros: Macro[]): void {
    this.macros = macros;
  }

  ngOnInit(): void {
    this.getMacros();
    this.getAccessories();
    this.getAccessoryTypes();
  }

}
