import {Component, OnInit} from '@angular/core';
import {RelayController} from '../models/relay-controller';
import {RelayService} from '../relay.service';

@Component({
  selector: 'app-relay',
  templateUrl: './relay.component.html',
  styleUrls: ['./relay.component.css']
})
export class RelayComponent implements OnInit {

  relayControllers: RelayController[];
  relayController: RelayController;

  constructor(private relayService: RelayService) {
  }

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
    const relayController: RelayController = new RelayController();
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
    relay.value = relay.value ^ Math.pow(2, index);
    this.relayService.updateRelayValue(relay);
  }

  ngOnInit(): void {
    this.resetRelayController();
    this.getRelayControllers();
  }

}
