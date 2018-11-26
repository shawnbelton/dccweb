import {Component, OnInit} from '@angular/core';
import {Macro} from '../models/macro';

@Component({
  selector: 'app-new-macro',
  templateUrl: './new-macro.component.html',
  styleUrls: ['./new-macro.component.css']
})
export class NewMacroComponent implements OnInit {

  macro: Macro;

  constructor() {
  }

  saved(event: string): void {
    this.macro = new Macro();
  }

  ngOnInit() {
    this.macro = new Macro();
  }

}
