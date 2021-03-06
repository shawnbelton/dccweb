import {Injectable} from '@angular/core';
import {BehaviorSubject, Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import {Macro} from './models/macro';

@Injectable({
  providedIn: 'root'
})
export class MacroService {

  private _macros: BehaviorSubject<Macro[]> = new BehaviorSubject([]);
  private macros: Observable<Macro[]> = this._macros.asObservable();

  constructor(private http: HttpClient) {
    this.fetchMacros();
  }

  fetchMacros(): void {
    this.http.get('/api/macros/all').subscribe((data: Macro[]) => {
      this._macros.next(data);
    }, error => console.log('Unable to get macros.'));
  }

  saveMacro(macro: Macro): void {
    this.http.post('/api/macros/save', macro).subscribe((data: Macro[]) => {
      this._macros.next(data);
    }, error => console.log('Unable to get macros.'));
  }

  deleteMacro(macro: Macro): void {
    this.http.post('/api/macros/delete', macro).subscribe((data: Macro[]) => {
      this._macros.next(data);
    }, error => console.log('Unable to get macros.'));
  }

  runMacroById(macroId: number): void {
    this.runMacro(this.findMacro(macroId));
  }

  runMacro(macro: Macro): void {
    this.http.post('/api/macros/run', macro).subscribe(data => {
      },
      error => console.log('Unable to run macro.'));
  }

  findMacro(macroId: number): Macro {
    let macro: Macro = new Macro();
    for (const iMacro of this._macros.getValue()) {
      if (iMacro.macroId === macroId) {
        macro = iMacro;
      }
    }
    return macro;
  }

  getMacroName(macroId: number): string {
    return this.findMacro(macroId).name;
  }

  getMacros(): Observable<Macro[]> {
    return this.macros;
  }
}
