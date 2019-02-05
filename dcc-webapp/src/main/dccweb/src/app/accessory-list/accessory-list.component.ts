import {Component, OnInit} from '@angular/core';
import {AccessoryDecoder} from '../models/accessory-decoder';
import {AccessoryDecoderService} from '../accessory-decoder.service';
import {DecoderAccessoryTypeOperation} from '../models/decoder-accessory-type-operation';
import {AccessoryOperation} from '../models/accessory-operation';

@Component({
  selector: 'app-accessory-list',
  templateUrl: './accessory-list.component.html',
  styleUrls: ['./accessory-list.component.css']
})
export class AccessoryListComponent implements OnInit {

  accessoryDecoders: AccessoryDecoder[];

  constructor(private accessoryDecoderService: AccessoryDecoderService) {
  }

  getAccessories(): void {
    this.accessoryDecoderService
      .getAccessories().subscribe(data => this.setAccessories(data));
  }

  setAccessories(accessoryDecoders: AccessoryDecoder[]): void {
    this.accessoryDecoders = accessoryDecoders;
  }

  isState(accessory: AccessoryDecoder, accessoryOperation: DecoderAccessoryTypeOperation): boolean {
    return accessory.currentValue === accessoryOperation.decoderOperationValue;
  }

  operateAccessory(accessory: AccessoryDecoder, operationValue: number): void {
    const accessoryOperation: AccessoryOperation = new AccessoryOperation();
    accessoryOperation.accessoryAddress = accessory.address;
    accessoryOperation.operationValue = operationValue;
    accessory.currentValue = operationValue;
    this.accessoryDecoderService.operateAccessory(accessoryOperation);
  }

  ngOnInit() {
    this.getAccessories();
  }

}
