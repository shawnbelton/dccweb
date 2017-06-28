/**
 * Created by shawn on 03/04/17.
 */
import {Component, OnInit} from "@angular/core";
import {BlockService} from "../services/block.service";
import {Block} from "../models/block";
import {Macro} from "../models/macro";
import {MacroService} from "../services/macro.service";

@Component({
    moduleId: module.id,
    templateUrl: './../html/block/block.html',
})
export class BlockComponent implements OnInit {

    blocks: Block[];
    macros: Macro[];
    block: Block;

    constructor(private blockService: BlockService, private macroService: MacroService) {}

    getBlocks(): void {
        this.blockService.getBlocks().subscribe(data => this.blocks = data);
    }

    getMacros(): void {
        this.macroService.getMacros().subscribe(macros => this.setMacros(macros));
    }

    setMacros(macros: Macro[]): void {
        this.macros = macros;
    }

    saveBlock(): void {
        if (this.block.macro.macroId == null || this.block.macro.macroId.toString() == "") {
            this.block.macro = null;
        }
        this.blockService.saveBlock(this.block);
        this.resetBlock();
    }

    deleteBlock(block: Block): void {
        this.blockService.deleteBlock(block);
        this.resetBlock();
    }

    resetBlock(): void {
        let block: Block = new Block();
        block.macro = new Macro();
        this.setBlock(block);
    }

    setBlock(block: Block): void {
        this.block = block;
    }

    startBlockEdit(block: Block): void {
        if (block.macro == null) {
            block.macro = new Macro();
        }
        this.setBlock(block);
    }

    cancelEdit(): void {
        this.resetBlock();
    }

    setBlockOccupancy(block: Block, state: boolean): void {
        block.occupied = state;
        this.blockService.setBlockOccupancy(block);
    }

    ngOnInit(): void {
        this.resetBlock();
        this.getMacros();
        this.getBlocks();
    }
}
