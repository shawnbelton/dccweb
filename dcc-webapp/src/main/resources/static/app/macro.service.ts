/**
 * Created by shawn on 02/12/16.
 */
import {Injectable} from "@angular/core";
import {BehaviorSubject, Observable} from "rxjs/Rx";
import {Macro} from "./macro";

@Injectable()
export class MacroService {

    private _macros: BehaviorSubject<Macro[]> = new BehaviorSubject([]);
    private macros: Observable<Macro[]> = this._macros.asObservable();

    private macroArray: Macro[] = new Array();

    saveMacro(macro: Macro): void {
        this.macroArray.push(macro);
        this._macros.next(this.macroArray);
    }

    getMacros(): Observable<Macro[]> {
        return this.macros;
    }

}