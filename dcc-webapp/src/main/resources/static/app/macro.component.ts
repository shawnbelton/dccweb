/**
 * Created by shawn on 28/11/16.
 */
import {Component, OnInit} from "@angular/core";
import {Macro} from "./macro";
import {MacroStep} from "./macro.step";
import {Train} from "./train";
import {TrainService} from "./train.service";
import {Decoder} from "./decoder";
import {DecoderFunction} from "./decoderFunction";
import {MacroService} from "./macro.service";
@Component({
    moduleId: module.id,
    templateUrl: '/macroedit/macro.html'
})
export class MacroComponent implements OnInit {

    macro: Macro;
    trains: Train[];
    macros: Macro[];

    constructor(private trainService: TrainService, private macroService: MacroService) {}

    getTrains(): void {
        this.trainService.getTrains().subscribe(trains => this.trains = trains);
    }

    getMacros(): void {
        this.macroService.getMacros().subscribe(macros => this.macros = macros);
    }

    setMacro(macro: Macro): void {
        this.macro = macro;
        this.macro.steps = this.macro.steps.sort((step1, step2) => step1.number - step2.number);
    }

    getMacro(): void {
        this.macroService.getMacro().subscribe(macro => this.setMacro(macro));
    }

    editMacro(macro: Macro): void {
        this.macroService.editMacro(macro);
    }

    saveMacro(): void {
        this.macroService.saveMacro(this.macro);
        this.macro = new Macro();
    }

    deleteMacro(macro: Macro): void {
        this.macroService.deleteMacro(macro);
        this.macro = new Macro();
    }

    runMacro(macro: Macro): void {
        this.macroService.runMacro(macro);
    }

    macroValid(): boolean {
        return this.macro.name != null && this.macro.name.length > 0 && this.macro.steps.length > 0;
    }

    saveMacroName(): void {
        this.macro.editing = false;
    }

    editMacroName(): void {
        this.macro.editing = true;
    }

    addStep(): void {
        let macroStep: MacroStep = new MacroStep();
        macroStep.number = this.macro.steps.length + 1;
        macroStep.editing = true;
        this.macro.steps.push(macroStep);
    }

    saveStep(step: MacroStep): void {
        step.editing = false;
    }

    editStep(step: MacroStep): void {
        step.editing = true;
    }

    fetchTrain(step: MacroStep): Train {
        let stepTrain: Train;
        for(let train of this.trains) {
            if (train.trainId == step.trainId) {
                stepTrain = train;
            }
        }
        return stepTrain;
    }

    fetchDecoder(step: MacroStep): Decoder {
        return this.fetchTrain(step).decoder;
    }

    isFirst(step: MacroStep): boolean {
        return step.number == 1;
    }

    isLast(step: MacroStep): boolean {
        return step.number == this.macro.steps.length;
    }

    moveUp(step: MacroStep): boolean {
        let stepNumber: number = step.number - 1;
        let prev: MacroStep = this.macro.steps[stepNumber - 1];
        this.macro.steps[stepNumber - 1] = step;
        this.macro.steps[stepNumber] = prev;
        this.renumber();
        return false;
    }

    moveDown(step: MacroStep): boolean {
        let stepNumber: number = step.number - 1;
        let next: MacroStep = this.macro.steps[stepNumber + 1];
        this.macro.steps[stepNumber + 1] = step;
        this.macro.steps[stepNumber] = next;
        this.renumber();
        return false;
    }

    removeStep(step: MacroStep): boolean {
        let stepNumber: number = step.number - 1;
        this.macro.steps.splice(stepNumber, 1);
        this.renumber();
        return false;
    }

    renumber(): void {
        let index: number = 1;
        for(let step of this.macro.steps) {
            step.number = index++;
        }
    }

    isTrainFunction(step: MacroStep): boolean {
        return step.type == 'decoderFunction' || step.type == 'setSpeed';
    }

    displayStep(step: MacroStep): string {
        let display: string = step.number.toString() + " ";
        switch(step.type) {
            case "delay":
                display = display + "Wait " + step.delay.toString() + " Seconds";
                break;
            case "decoderFunction":
                display = display + this.displayFunction(step);
                break;
            case "setSpeed":
                display = display + this.displaySetSpeed(step);
        }
        return display;
    }

    displayFunction(step: MacroStep): string {
        let train: Train = this.fetchTrain(step);
        let display: string = "Turn " + this.getState(step.functionStatus);
        display = display + " " + this.displayFunctionName(train, step.functionNumber);
        display = display + " on " + this.displayTrainInfo(train);
        return display;
    }

    displaySetSpeed(step: MacroStep): string {
        let train: Train = this.fetchTrain(step);
        let display: string = "Set speed to " + step.speed;
        display = display + " on " + this.displayTrainInfo(train);
        return display;
    }

    displayTrainInfo(train: Train): string {
        return train.number + " " + train.name;
    }

    displayFunctionName(train: Train, functionNumber: number): string {
        let decoderFunction: DecoderFunction;
        for(let iFunction of train.decoder.decoderFunctions) {
            if (iFunction.number == functionNumber) {
                decoderFunction = iFunction;
            }
        }
        return decoderFunction.name;
    }

    getState(functionStatus: string): String {
        let value: string;
        if (functionStatus == "true") {
            value = "On";
        } else {
            value = "Off";
        }
        return value;
    }

    ngOnInit(): void {
        this.macro = new Macro();
        this.getMacro();
        this.getMacros();
        this.getTrains();
    }
}