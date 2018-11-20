import {Component, Input} from '@angular/core';
import {Block} from '../models/block';
import {BlockService} from '../block.service';

@Component({
  selector: 'app-block-item',
  templateUrl: './block-item.component.html',
  styleUrls: ['./block-item.component.css']
})
export class BlockItemComponent {

  @Input() block: Block;

  constructor(private blockService: BlockService) {
  }

  setBlockOccupancy(state: boolean): void {
    this.block.occupied = state;
    this.blockService.setBlockOccupancy(this.block);
  }

  startBlockEdit(): void {
    this.blockService.setBlock(this.block);
  }

  deleteBlock(): void {
    this.blockService.deleteBlock(this.block);
  }

}
