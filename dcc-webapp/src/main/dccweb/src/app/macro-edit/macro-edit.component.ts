import {Component, OnDestroy, OnInit} from '@angular/core';
import {Macro} from '../models/macro';
import {MacroService} from '../macro.service';
import {ActivatedRoute, Router} from '@angular/router';


@Component({
  selector: 'app-macro-edit',
  templateUrl: './macro-edit.component.html',
  styleUrls: ['./macro-edit.component.css']
})
export class MacroEditComponent implements OnInit, OnDestroy {

  macro: Macro;
  private sub: any;

  constructor(private macroService: MacroService, private route: ActivatedRoute,
              private router: Router) {}

  saved(event: string): void {
    this.router.navigate(['/macro-edit']);
  }

  setMacro(macro: Macro): void {
    this.macro = macro;
    this.macro.steps = this.macro.steps.sort((step1, step2) => step1.number - step2.number);
  }

  getMacro(id: number): void {
    this.setMacro(this.macroService.findMacro(id));
  }

  ngOnInit(): void {
    this.sub = this.route.params.subscribe(params => {
      this.getMacro(+params['id']);
    });
  }

  ngOnDestroy(): void {
    this.sub.unsubscribe();
  }

}
