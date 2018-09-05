/**
 * Created by shawn on 16/11/16.
 */
import {Component, OnInit} from "@angular/core";
import {Decoder} from "../models/decoder";
import {DecoderService} from "../services/decoder.service";
import {MacroService} from "../services/macro.service";
import {DecoderFunction} from "../models/decoderFunction";
import {Macro} from "../models/macro";
import {LinkedMacro} from "../models/linked.macro";
import {DecoderSetting} from "../models/decoderSetting";

@Component({
  moduleId: module.id,
  templateUrl: './../html/decoder/decoder.html'
})
export class DecoderComponent implements OnInit {

  decoders: Decoder[];
  decoderSettings: DecoderSetting[];
  macros: Macro[];
  currentDecoder: Decoder;
  availableFunctions: number[];
  newDecoderFunction: DecoderFunction;
  linkedMacro: LinkedMacro;
  decoderTabState: string;

  constructor(private decoderService: DecoderService,
              private macroService: MacroService) {
    this.decoderTabState = "FUNCTIONS";
  }

  setFunctionsTab(): boolean {
    this.decoderTabState = "FUNCTIONS";
    return false;
  }

  setMacrosTab(): boolean {
    this.decoderTabState = "MACROS";
    return false;
  }

  isNotEmptySettings(): boolean {
    let isNotEmpty: boolean = false;
    if (this.decoderSettings != null) {
      isNotEmpty = this.decoderSettings.length > 0;
    }
    return isNotEmpty;
  }

  isNotEmptyDecoder(): boolean {
    return this.currentDecoder != null && this.currentDecoder.currentAddress != null;
  }

  isFunctionsTab(): boolean {
    return this.isNotEmptyDecoder() && this.decoderTabState == "FUNCTIONS";
  }

  isMacrosTab(): boolean {
    return this.isNotEmptyDecoder() && this.decoderTabState == "MACROS";
  }

  readDecoder(): void {
    this.decoderService.readDecoder();
  }

  readFullDecoder(): void {
    this.decoderService.readFullDecoder();
  }

  writeCVs(): void {
    this.decoderService.writeCVs(this.decoderSettings);
  }

  editDecoder(decoder: Decoder): void {
    this.decoderService.fetchDecoder(decoder.decoderId);
  }

  refreshDecoders(): void {
    this.decoderService.fetchDecoders();
  }

  getDecoders(): void {
    this.decoderService
      .getDecoders().subscribe(decoders => this.decoders = decoders);
    this.decoderService
      .getDecoder().subscribe(decoder => this.setCurrentDecoder(decoder));
  }

  setDecoderSettings(decoderSettingValues: DecoderSetting[]) {
    this.decoderSettings = decoderSettingValues;
  }

  getDecoderSettings(): void {
    this.decoderService.getDecoderSettings()
      .subscribe(decoderSettings => this.setDecoderSettings(decoderSettings));
  }

  getMacros(): void {
    this.macroService.getMacros().subscribe(macros => this.macros = macros);
  }

  addDecoderFunction(): void {
    this.newDecoderFunction.decoderId = this.currentDecoder.decoderId;
    this.decoderService.addDecoderFunction(this.newDecoderFunction);
  }

  linkMacro(): void {
    this.linkedMacro.decoderId = this.currentDecoder.decoderId;
    this.decoderService.linkMacro(this.linkedMacro);
  }

  deleteDecoderFunction(decoderFunction: DecoderFunction): void {
    this.decoderService.deleteDecoderFunction(decoderFunction);
  }

  unlinkMacro(linkedMacro: LinkedMacro): void {
    this.decoderService.unlinkMacro(linkedMacro);
  }

  getMacroName(macroId: number): string {
    return this.macroService.getMacroName(macroId);
  }

  private setCurrentDecoder(decoder: Decoder): void {
    this.currentDecoder = decoder;
    this.availableFunctions = new Array();
    for (let index = 0; index < 29; index++) {
      if (!this.hasFunction(index)) {
        this.availableFunctions.push(index);
      }
    }
    this.newDecoderFunction = new DecoderFunction();
    this.linkedMacro = new LinkedMacro();
    if (this.availableFunctions.length > 0) {
      this.newDecoderFunction.number = this.availableFunctions[0];
    }
  }

  private hasFunction(index: number): boolean {
    let found = false;
    if (this.currentDecoder != null && this.currentDecoder.decoderFunctions != null) {
      for (let decoderFunction of this.currentDecoder.decoderFunctions) {
        if (decoderFunction.number == index) {
          found = true;
        }
      }
    }
    return found;
  }

  ngOnInit(): void {
    this.getDecoders();
    this.getMacros();
    this.getDecoderSettings();
  }
}
