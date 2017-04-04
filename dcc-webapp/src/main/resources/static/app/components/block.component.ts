/**
 * Created by shawn on 03/04/17.
 */
import {Component, OnInit} from "@angular/core";
import {BlockService} from "../services/block.service";
import {Block} from "../models/block";

@Component({
    moduleId: module.id,
    templateUrl: '../../block/block.html',
})
export class BlockComponent implements OnInit {

    blocks: Block[];
    block: Block;

    constructor(private blockService: BlockService) {}

    getBlocks(): void {
        this.blockService.getBlocks().subscribe(data => this.blocks = data);
    }

    saveBlock(): void {
        this.blockService.saveBlock(this.block);
        this.resetBlock();
    }

    resetBlock(): void {
        this.setBlock(new Block());
    }

    setBlock(block: Block): void {
        this.block = block;
    }

    startBlockEdit(block: Block): void {
        this.setBlock(block);
    }

    cancelEdit(): void {
        this.resetBlock();
    }

    ngOnInit(): void {
        this.resetBlock();
        this.getBlocks();
    }
}