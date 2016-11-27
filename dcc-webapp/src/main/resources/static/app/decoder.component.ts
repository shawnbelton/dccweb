/**
 * Created by shawn on 16/11/16.
 */
import {Component, OnInit} from "@angular/core";
import {Decoder} from "./decoder";
import {DecoderService} from "./decoder.service";
import {DecoderFunction} from "./decoderFunction";

@Component({
    moduleId: module.id,
    templateUrl: '/decoder/decoder.html'
})
export class DecoderComponent implements OnInit {

    decoders: Decoder[];
    currentDecoder: Decoder;
    availableFunctions: number[];
    newDecoderFunction: DecoderFunction;

    constructor(private decoderService: DecoderService) {}

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

    addDecoderFunction(): void {
        this.newDecoderFunction.decoderId = this.currentDecoder.decoderId;
        this.decoderService.addDecoderFunction(this.newDecoderFunction);
    }

    deleteDecoderFunction(decoderFunction: DecoderFunction): void {
        this.decoderService.deleteDecoderFunction(decoderFunction);
    }

    private setCurrentDecoder(decoder: Decoder): void {
        this.currentDecoder = decoder;
        this.availableFunctions = new Array();
        for(var index = 0; index < 29; index++) {
            if (!this.hasFunction(index)) {
                this.availableFunctions.push(index);
            }
        }
        this.newDecoderFunction = new DecoderFunction();
        if (this.availableFunctions.length>0) {
            this.newDecoderFunction.number = this.availableFunctions[0];
        }
    }

    private hasFunction(index: number): boolean {
        var found = false;
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
    }
}