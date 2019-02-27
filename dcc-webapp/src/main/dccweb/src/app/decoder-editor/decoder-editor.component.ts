import {Component, Input, OnInit} from '@angular/core';
import {Decoder} from "../models/decoder";
import {DecoderFunction} from "../models/decoder-function";
import {DecoderService} from "../decoder.service";
import {MacroService} from "../macro.service";
import {LinkedMacro} from "../models/linked-macro";
import {Macro} from "../models/macro";

@Component({
  selector: 'app-decoder-editor',
  templateUrl: './decoder-editor.component.html',
  styleUrls: ['./decoder-editor.component.css']
})
export class DecoderEditorComponent implements OnInit {

  @Input() editDecoder: Decoder;

  newDecoderFunction: DecoderFunction;
  linkedMacro: LinkedMacro;
  macros: Macro[];
  decoderTabState: string;

  constructor(private decoderService: DecoderService,
              private macroService: MacroService) {
    this.setFunctionsTab();
    this.newDecoderFunction = new DecoderFunction();
    this.linkedMacro = new LinkedMacro();
  }

  isNotEmptyDecoder(): boolean {
    return this.editDecoder != null && this.editDecoder.currentAddress != null;
  }

  setFunctionsTab(): boolean {
    this.decoderTabState = 'FUNCTIONS';
    return false;
  }

  setMacrosTab(): boolean {
    this.decoderTabState = 'MACROS';
    return false;
  }

  isFunctionsTab(): boolean {
    return this.isNotEmptyDecoder() && this.decoderTabState === 'FUNCTIONS';
  }

  isMacrosTab(): boolean {
    return this.isNotEmptyDecoder() && this.decoderTabState === 'MACROS';
  }

  getAvailableFunctions(): number[] {
    let availableFunctions: number[] = [];
    for(let index = 0; index < 29; index++) {
      if (!this.hasFunction(index)) {
        availableFunctions.push(index);
      }
    }
    return availableFunctions;
  }

  private hasFunction(index: number): boolean {
    let found = false;
    if (this.editDecoder != null && this.editDecoder.decoderFunctions != null) {
      for (const decoderFunction of this.editDecoder.decoderFunctions) {
        if (decoderFunction.number === index) {
          found = true;
        }
      }
    }
    return found;
  }

  addDecoderFunction(): void {
    this.newDecoderFunction.decoderId = this.editDecoder.decoderId;
    this.decoderService.addDecoderFunction(this.newDecoderFunction);
    this.newDecoderFunction = new DecoderFunction();
  }

  deleteDecoderFunction(decoderFunction: DecoderFunction): void {
    this.decoderService.deleteDecoderFunction(decoderFunction);
  }

  setDecoder(decoder: Decoder): void {
    if (decoder != null) {
      this.editDecoder = decoder;
      this.linkedMacro.decoderId = decoder.decoderId;
    }
  }

  unlinkMacro(linkedMacro: LinkedMacro): void {
    this.decoderService.unlinkMacro(linkedMacro);
  }

  getMacroName(macroId: number): string {
    return this.macroService.getMacroName(macroId);
  }

  linkMacro(): void {
    this.linkedMacro.decoderId = this.editDecoder.decoderId;
    this.decoderService.linkMacro(this.linkedMacro);
    this.linkedMacro = new LinkedMacro();
  }

  ngOnInit() {
    this.decoderService.getDecoder().subscribe(decoder => this.setDecoder(decoder));
    this.macroService.getMacros().subscribe(macros => this.macros = macros);
  }

}
