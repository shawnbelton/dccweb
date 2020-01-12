import {Component, OnInit} from '@angular/core';
import {AccessoryDecoder} from '../models/accessory-decoder';
import {DecoderAccessoryType} from '../models/decoder-accessory-type';

@Component({
  selector: 'app-new-accessory',
  templateUrl: './new-accessory.component.html',
  styleUrls: ['./new-accessory.component.css']
})
export class NewAccessoryComponent implements OnInit {

  accessory: AccessoryDecoder;

  constructor() {
  }

  newAccessory(): void {
    this.accessory = new AccessoryDecoder();
    this.accessory.accessoryDecoderType = new DecoderAccessoryType();
  }

  saved(event: string): void {
    this.newAccessory();
  }

  ngOnInit() {
    this.newAccessory();
  }

}
