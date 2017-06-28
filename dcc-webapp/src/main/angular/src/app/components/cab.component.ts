/**
 * Created by shawn on 22/11/16.
 */
import {Component, OnInit} from "@angular/core";
import {Cab} from "../models/cab";
import {CabService} from "../services/cab.service";
import {CabFunction} from "../models/cabFunction";
import {Macro} from "../models/macro";
import {MacroService} from "../services/macro.service";
@Component({
    moduleId: module.id,
    templateUrl: './../html/cab/cab.html',
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

    setCab(cab: Cab): void {
        this.cab = cab;
    }

    getCab(): void {
        this.cabService.getCab().subscribe(cab => this.setCab(cab));
    }

    ngOnInit(): void {
        this.getCab();
    }
}
