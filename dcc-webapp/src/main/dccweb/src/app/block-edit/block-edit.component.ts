import {Component, OnDestroy, OnInit} from '@angular/core';
import {Macro} from '../models/macro';
import {Block} from '../models/block';
import {BlockService} from '../block.service';
import {MacroService} from '../macro.service';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-block-edit',
  templateUrl: './block-edit.component.html',
  styleUrls: ['./block-edit.component.css']
})
export class BlockEditComponent implements OnInit, OnDestroy {

  macros: Macro[];
  block: Block;
  sub: any;

  constructor(private blockService: BlockService,
              private macroService: MacroService,
              private route: ActivatedRoute,
              private router: Router) {
  }

  getBlock(blockId: string): void {
    this.block = this.blockService.getBlock(blockId);
  }

  resetBlock(): void {
    this.router.navigate(['/blocks']);
  }

  saveBlock(): void {
    this.blockService.saveBlock(this.block);
    this.resetBlock();
  }

  cancelEdit(): void {
    this.resetBlock();
  }

  getMacros(): void {
    this.macroService.getMacros().subscribe(macros => this.setMacros(macros));
  }

  setMacros(macros: Macro[]): void {
    this.macros = macros;
  }

  ngOnInit(): void {
    this.getMacros();
    this.sub = this.route.params.subscribe(params => {
      this.getBlock(params['id']);
    });
  }

  ngOnDestroy(): void {
    this.sub.unsubscribe();
  }
}
