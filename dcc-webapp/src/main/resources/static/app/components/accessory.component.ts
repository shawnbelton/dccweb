/**
 * Created by shawn on 28/02/17.
 */
import {Component, OnInit} from "@angular/core";
import {AccessoryDecoder} from "../models/accessoryDecoder";
import {AccessoryDecoderService} from "../services/accessoryDecoder.service";
import {DecoderAccessoryType} from "../models/decoderAccessoryType";
import {AccessoryOperation} from "../models/accessoryOperation";
import {DecoderAccessoryTypeOperation} from "../models/decoderAccessoryTypeOpertation";

@Component({
    moduleId: module.id,
    templateUrl: '../../accessory/accessories.html'
})
export class AccessoryComponent implements OnInit {

    accessoryDecoders: AccessoryDecoder[];
    accessoryDecoderTypes: DecoderAccessoryType[];
    editAccessory: AccessoryDecoder;

    constructor(private accessoryDecoderService: AccessoryDecoderService) {
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
        this.setCurrentAccessory(newAccessory);
    }

    cancelEdit(): void {
        this.newAccessory();
    }

    saveAccessory(): void {
        this.accessoryDecoderService.saveAccessory(this.editAccessory);
        this.newAccessory();
    }

    operateAccessory(accessoryAddress: number, operationValue: number): void {
        let accessoryOperation: AccessoryOperation = new AccessoryOperation();
        accessoryOperation.accessoryAddress = accessoryAddress;
        accessoryOperation.operationValue = operationValue;
        this.accessoryDecoderService.operateAccessory(accessoryOperation);
    }

    isState(accessory: AccessoryDecoder, accessoryOperation: DecoderAccessoryTypeOperation): boolean {
        return accessory.currentValue == accessoryOperation.decoderOperationValue;
    }

    getAccessories(): void {
        this.accessoryDecoderService
            .getAccessories().subscribe(data => this.accessoryDecoders = data);
        this.accessoryDecoderService
            .getAccessory().subscribe(accessory => this.setCurrentAccessory(accessory));
    }

    getAccessoryTypes(): void {
        this.accessoryDecoderService
            .getAccessoryTypes().subscribe(data => this.accessoryDecoderTypes = data);
    }

    ngOnInit(): void {
        this.getAccessories();
        this.getAccessoryTypes();
    }
}