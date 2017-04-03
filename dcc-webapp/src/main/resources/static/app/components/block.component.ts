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

    constructor(private blockService: BlockService) {}

    getBlocks(): void {
        this.blockService.getBlocks().subscribe(data => this.blocks = data);
    }

    ngOnInit(): void {
        this.getBlocks();
    }
}