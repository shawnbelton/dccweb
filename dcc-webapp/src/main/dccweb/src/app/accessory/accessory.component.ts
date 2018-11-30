import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {AccessoryDecoder} from '../models/accessory-decoder';
import {DecoderAccessoryType} from '../models/decoder-accessory-type';
import {Macro} from '../models/macro';
import {AccessoryDecoderService} from '../accessory-decoder.service';
import {MacroService} from '../macro.service';

@Component({
  selector: 'app-accessory',
  templateUrl: './accessory.component.html',
  styleUrls: ['./accessory.component.css']
})
export class AccessoryComponent implements OnInit {

  @Input() editAccessory: AccessoryDecoder;
  @Output() saved = new EventEmitter<string>();
  accessoryDecoderTypes: DecoderAccessoryType[];
  macros: Macro[];

  constructor(private accessoryDecoderService: AccessoryDecoderService, private macroService: MacroService) {
  }

  cancelEdit(): void {
    this.saved.emit('saved');
  }

  saveAccessory(): void {
    this.accessoryDecoderService.saveAccessory(this.editAccessory);
    this.saved.emit('saved');
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
    this.getAccessoryTypes();
  }

}
