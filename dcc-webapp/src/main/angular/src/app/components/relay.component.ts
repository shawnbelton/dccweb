/**
 * Created by shawn on 07/06/17.
 */

import {Component, OnInit} from '@angular/core';
import {RelayController} from '../models/relayController';
import {RelayService} from '../services/relay.service';

@Component({
    moduleId: module.id,
    templateUrl: './../html/relay/relay.html',
})
export class RelayComponent implements OnInit {

    relayControllers: RelayController[];
    relayController: RelayController;

    constructor(private relayService: RelayService) {}


    getRelayControllers(): void {
        this.relayService.getRelayControllers().subscribe(data => this.relayControllers = data);
    }

    saveRelayController(): void {
        this.relayService.saveRelayController(this.relayController);
        this.resetRelayController();
    }

    deleteRelayController(): void {
        this.resetRelayController();
    }

    resetRelayController(): void {
        let relayController: RelayController = new RelayController();
        this.setRelayController(relayController);
    }

    setRelayController(relayController: RelayController): void {
        this.relayController = relayController;
    }

    startRelayControllerEdit(relayController: RelayController): void {
        this.setRelayController(relayController);
    }

    cancelEdit(): void {
        this.resetRelayController();
    }

    showName(relay: RelayController, index: number): string {
        return '' + (index + 1);
    }

    switchStatus(relay: RelayController, index: number): boolean {
        return (relay.value & Math.pow(2, index)) > 0;
    }

    switchChange(relay: RelayController, index: number): void {
        relay.value ^= Math.pow(2, index);
        this.relayService.updateRelayValue(relay);
    }

    ngOnInit(): void {
        this.resetRelayController();
        this.getRelayControllers();
    }
}
