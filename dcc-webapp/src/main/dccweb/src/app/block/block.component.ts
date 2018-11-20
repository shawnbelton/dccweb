import {Component, OnInit} from '@angular/core';
import {Macro} from '../models/macro';
import {MacroService} from '../macro.service';
import {Block} from '../models/block';
import {BlockService} from '../block.service';

@Component({
  selector: 'app-block',
  templateUrl: './block.component.html',
  styleUrls: ['./block.component.css']
})
export class BlockComponent implements OnInit {

  blocks: Block[];
  macros: Macro[];
  block: Block;

  constructor(private blockService: BlockService, private macroService: MacroService) {
  }

  getBlocks(): void {
    this.blockService.getBlocks().subscribe(data => this.blocks = data);
  }

  getBlock(): void {
    this.blockService.getBlock().subscribe(data => this.block = data);
  }

  getMacros(): void {
    this.macroService.getMacros().subscribe(macros => this.setMacros(macros));
  }

  setMacros(macros: Macro[]): void {
    this.macros = macros;
  }

  saveBlock(): void {
    this.blockService.saveBlock(this.block);
    this.resetBlock();
  }

  deleteBlock(block: Block): void {
    this.blockService.deleteBlock(block);
    this.resetBlock();
  }

  resetBlock(): void {
    const block: Block = new Block();
    this.blockService.setBlock(block);
  }

  startBlockEdit(block: Block): void {
    this.blockService.setBlock(block);
  }

  cancelEdit(): void {
    this.resetBlock();
  }

  setBlockOccupancy(block: Block, state: boolean): void {
    block.occupied = state;
    this.blockService.setBlockOccupancy(block);
  }

  ngOnInit(): void {
    this.getBlock();
    this.resetBlock();
    this.getMacros();
    this.getBlocks();
  }

}
