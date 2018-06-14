import {Component, OnInit} from "@angular/core";
import {RelayService} from "./relay.service";

@Component({
  selector: 'relay-app',
  templateUrl: './html/relay.html',
  styleUrls: ['./css/relay-values.css']
})
export class AppRelayComponent implements OnInit {

  private values: number[];

  constructor(private relayService: RelayService) {}

  getRelayValues(): number[] {
    return this.values;
  }

  updateRelayValues(controllerValue: number): void {
    let relayValues: number[] = [];
    for(let index = 0; index < 8; index++) {
      let flagValue: number = 2 ** index;
      if ((controllerValue & flagValue) > 0) {
        relayValues.push(1);
      } else {
        relayValues.push(0);
      }
    }
    this.values = relayValues;
  }

  ngOnInit(): void {
    this.updateRelayValues(0);
    this.relayService.getValues().subscribe(data => this.updateRelayValues(data));
  }
}
