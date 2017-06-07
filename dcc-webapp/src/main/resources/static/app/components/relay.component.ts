/**
 * Created by shawn on 07/06/17.
 */

import {Component, OnInit} from "@angular/core";
import {RelayController} from "../models/relayController";
import {RelayService} from "../services/relay.service";
@Component({
    moduleId: module.id,
    templateUrl: '../../relay/relay.html',
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

    ngOnInit(): void {
        this.resetRelayController();
        this.getRelayControllers();
    }
}