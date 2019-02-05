import {Component, OnInit} from '@angular/core';
import {RelayController} from '../models/relay-controller';
import {RelayService} from '../relay.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-relay-list',
  templateUrl: './relay-list.component.html',
  styleUrls: ['./relay-list.component.css']
})
export class RelayListComponent implements OnInit {

  relayControllers: RelayController[];

  constructor(private relayService: RelayService,
              private router: Router) { }

  getRelayControllers(): void {
    this.relayService.getRelayControllers().subscribe(data => this.relayControllers = data);
  }

  deleteRelayController(relay: RelayController): void {
    this.router.navigate(['/relays']);
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

  ngOnInit() {
    this.getRelayControllers();
  }

}
