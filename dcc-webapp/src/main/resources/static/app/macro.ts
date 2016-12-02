/**
 * Created by shawn on 30/11/16.
 */
import {MacroStep} from "./macro.step";

export class Macro {
    name: string;
    editing: boolean;
    steps: MacroStep[];

    constructor() {
        this.steps = new Array();
    }
}
