/**
 * Created by shawn on 28/02/17.
 */
import {Component, OnInit} from "@angular/core";
import {AccessoryDecoder} from "../models/accessoryDecoder";
import {AccessoryDecoderService} from "../services/accessoryDecoder.service";
import {DecoderAccessoryType} from "../models/decoderAccessoryType";
import {AccessoryOperation} from "../models/accessoryOperation";
import {DecoderAccessoryTypeOperation} from "../models/decoderAccessoryTypeOpertation";
import {Macro} from "../models/macro";
import {MacroService} from "../services/macro.service";

@Component({
    moduleId: module.id,
    templateUrl: './../html/accessory/accessories.html'
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
        let newAccessory: AccessoryDecoder = new AccessoryDecoder();
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
        let accessoryOperation: AccessoryOperation = new AccessoryOperation();
        accessoryOperation.accessoryAddress = accessory.address;
        accessoryOperation.operationValue = operationValue;
        accessory.currentValue = operationValue;
        this.accessoryDecoderService.operateAccessory(accessoryOperation);
    }

    isState(accessory: AccessoryDecoder, accessoryOperation: DecoderAccessoryTypeOperation): boolean {
        return accessory.currentValue == accessoryOperation.decoderOperationValue;
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
