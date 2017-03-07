/**
 * Created by shawn on 16/11/16.
 */
import {Component, OnInit} from "@angular/core";
import {Decoder} from "./decoder";
import {DecoderService} from "./decoder.service";
import {MacroService} from "./macro.service";
import {DecoderFunction} from "./decoderFunction";
import {Macro} from "./macro";
import {LinkedMacro} from "./linked.macro";

@Component({
    moduleId: module.id,
    templateUrl: '/decoder/decoder.html'
})
export class DecoderComponent implements OnInit {

    decoders: Decoder[];
    macros: Macro[];
    currentDecoder: Decoder;
    availableFunctions: number[];
    newDecoderFunction: DecoderFunction;
    linkedMacro: LinkedMacro;
    decoderTabState: string;

    constructor(private decoderService: DecoderService, private macroService: MacroService) {
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

    isEmptyDecoder(): boolean {
        return this.currentDecoder != null && this.currentDecoder.currentAddress != null;
    }

    isFunctionsTab(): boolean {
        return this.isEmptyDecoder() && this.decoderTabState=="FUNCTIONS";
    }

    isMacrosTab(): boolean {
        return this.isEmptyDecoder() && this.decoderTabState=="MACROS";
    }

    readDecoder(): void {
        this.decoderService.readDecoder();
    }

    editDecoder(decoder: Decoder): void {
        this.decoderService.fetchDecoder(decoder.decoderId);
    }

    getDecoders(): void {
        this.decoderService
            .getDecoders().subscribe(decoders => this.decoders = decoders);
        this.decoderService
            .getDecoder().subscribe(decoder => this.setCurrentDecoder(decoder));
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
        for(let macro of this.macros) {
            if (macro.macroId == this.linkedMacro.macroId) {
                this.linkedMacro.macro = macro;
            }
        }
        this.decoderService.linkMacro(this.linkedMacro);
    }

    deleteDecoderFunction(decoderFunction: DecoderFunction): void {
        this.decoderService.deleteDecoderFunction(decoderFunction);
    }

    unlinkMacro(linkedMacro: LinkedMacro): void {
        this.decoderService.unlinkMacro(linkedMacro);
    }

    private setCurrentDecoder(decoder: Decoder): void {
        this.currentDecoder = decoder;
        this.availableFunctions = new Array();
        for(let index = 0; index < 29; index++) {
            if (!this.hasFunction(index)) {
                this.availableFunctions.push(index);
            }
        }
        this.newDecoderFunction = new DecoderFunction();
        this.linkedMacro = new LinkedMacro();
        if (this.availableFunctions.length>0) {
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
    }
}