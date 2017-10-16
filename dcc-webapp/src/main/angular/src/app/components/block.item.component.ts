/**
 * Created by shawn on 07/07/17.
 */
import {Component, Input} from "@angular/core";
import {Block} from "../models/block";
import {BlockService} from "../services/block.service";
import {Macro} from "../models/macro";

@Component({
  moduleId: module.id,
  selector: 'block-item',
  inputs: ['block'],
  templateUrl: './../html/block/blockItem.html'
})
export class BlockItem {

  @Input() block: Block;

  constructor(private blockService: BlockService) {}

  setBlockOccupancy(state: boolean): void {
    this.block.occupied = state;
    this.blockService.setBlockOccupancy(this.block);
  }

  startBlockEdit(): void {
    if (this.block.macro == null) {
      this.block.macro = new Macro();
    }
    this.blockService.setBlock(this.block);
  }

  deleteBlock(): void {
    this.blockService.deleteBlock(this.block);
  }
}