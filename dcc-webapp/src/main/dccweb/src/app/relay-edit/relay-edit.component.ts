import {Component, OnDestroy, OnInit} from '@angular/core';
import {RelayController} from '../models/relay-controller';
import {RelayService} from '../relay.service';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-relay-edit',
  templateUrl: './relay-edit.component.html',
  styleUrls: ['./relay-edit.component.css']
})
export class RelayEditComponent implements OnInit, OnDestroy {

  relayController: RelayController;
  private sub: any;

  constructor(private relayService: RelayService,
              private route: ActivatedRoute,
              private router: Router) {
  }

  getRelayController(controllerId: string): void {
    this.relayController = this.relayService.getRelayController(controllerId);
  }

  saveRelayController(): void {
    this.relayService.saveRelayController(this.relayController);
    this.resetRelayController();
  }

  resetRelayController(): void {
    this.router.navigate(['/relays']);
  }

  cancelEdit(): void {
    this.resetRelayController();
  }

  ngOnInit() {
    this.sub = this.route.params.subscribe(params => {
      this.getRelayController(params['id']);
    });
  }

  ngOnDestroy(): void {
    this.sub.unsubscribe();
  }

}
