/**
 * Created by shawn on 22/11/16.
 */
import {Component, OnInit} from "@angular/core";
import {Cab} from "./cab";
import {CabService} from "./cab.service";
import {CabFunction} from "./cabFunction";
import {Macro} from "./macro";
import {MacroService} from "./macro.service";
@Component({
    moduleId: module.id,
    templateUrl: '/cab/cab.html',
    selector: 'dcc-cab'
})
export class CabComponent implements OnInit {

    cab: Cab;

    constructor(private cabService: CabService, private macroService: MacroService) {}

    speedChange(): void {
        this.updateCab();
    }

    up(): void {
        this.cab.direction = 'UP';
        this.updateCab();
    }

    down(): void {
        this.cab.direction = 'DOWN';
        this.updateCab();
    }

    halt(): void {
        this.cab.speed = 0;
        this.updateCab();
    }

    stop(): void {
        if (this.cab.direction == 'UP') {
            this.cab.speed = 0;
            this.cab.direction = 'FSTOP';
        } else if (this.cab.direction == 'DOWN') {
            this.cab.speed = 0;
            this.cab.direction = 'RSTOP';
        }
        this.updateCab();
    }

    updateCab(): void {
        this.cabService.updateCab(this.cab);
    }

    updateCabFunction(): void {
        this.cabService.updateCabFunction(this.cab);
    }

    runMacro(macro: Macro): void {
        this.macroService.runMacro(macro);
    }

    toggleFunction(cabFunction: CabFunction): void {
        cabFunction.state = !cabFunction.state;
        this.updateCabFunction();
    }

    getCab(): void {
        this.cabService.getCab().subscribe(cab => this.cab = cab);
    }

    ngOnInit(): void {
        this.getCab();
    }
}