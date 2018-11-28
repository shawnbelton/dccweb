import {Component, OnInit} from '@angular/core';
import {Block} from '../models/block';
import {BlockService} from '../block.service';

@Component({
  selector: 'app-block-list',
  templateUrl: './block-list.component.html',
  styleUrls: ['./block-list.component.css']
})
export class BlockListComponent implements OnInit {

  blocks: Block[];

  constructor(private blockService: BlockService) {
  }

  getBlocks(): void {
    this.blockService.getBlocks().subscribe(data => this.blocks = data);
  }

  ngOnInit() {
    this.getBlocks();
  }

}
