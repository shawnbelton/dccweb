import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {Macro} from '../models/macro';
import {MacroService} from '../macro.service';

@Component({
  selector: 'app-macro-list',
  templateUrl: './macro-list.component.html',
  styleUrls: ['./macro-list.component.css']
})
export class MacroListComponent implements OnInit {

  @Output() deleted = new EventEmitter<string>();
  macros: Macro[];

  constructor(private macroService: MacroService) { }

  getMacros(): void {
    this.macroService.getMacros().subscribe(macros => this.macros = macros);
  }

  deleteMacro(macro: Macro): void {
    this.macroService.deleteMacro(macro);
    this.deleted.emit('deleted');
  }

  runMacro(macroId: number): void {
    this.macroService.runMacroById(macroId);
  }

  ngOnInit() {
    this.getMacros();
  }

}
