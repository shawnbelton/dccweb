/**
 * Created by shawn on 02/12/16.
 */
import {Injectable} from "@angular/core";
import {BehaviorSubject, Observable} from "rxjs/Rx";
import {Macro} from "./macro";
import {Http} from "@angular/http";

@Injectable()
export class MacroService {

    private _macros: BehaviorSubject<Macro[]> = new BehaviorSubject([]);
    private macros: Observable<Macro[]> = this._macros.asObservable();

    private _macro: BehaviorSubject<Macro> = new BehaviorSubject(new Macro());
    private macro: Observable<Macro> = this._macro.asObservable();

    constructor(private http: Http) {
        this.fetchMacros();
    }

    editMacro(macro: Macro): void {
        this.http.get("/macros/" + macro.macroId.toString()).map(response => response.json())
            .subscribe(data => {
                this._macro.next(data);
            }, error => console.log("Unable to get macro."));
    }

    fetchMacros(): void {
        this.http.get("/macros").map(response => response.json()).subscribe(data => {
            this._macros.next(data);
        }, error => console.log("Unable to get macros."));
    }

    saveMacro(macro: Macro): void {
        this.http.post("/macros/save", macro).map(response => response.json()).subscribe(data => {
            this._macros.next(data);
        }, error => console.log("Unable to get macros."));
    }

    deleteMacro(macro: Macro): void {
        this.http.post("/macros/delete", macro).map(response => response.json()).subscribe(data => {
            this._macros.next(data);
        }, error => console.log("Unable to get macros."));
    }

    runMacro(macro: Macro): void {
        this.http.post("/macros/run", macro).map(response => response.json()).subscribe(data => {},
        error => console.log("Unable to run macro."));
    }

    getMacro(): Observable<Macro> {
        return this.macro;
    }

    getMacros(): Observable<Macro[]> {
        return this.macros;
    }

}