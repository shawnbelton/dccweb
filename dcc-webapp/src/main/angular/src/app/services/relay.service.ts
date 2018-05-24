/**
 * Created by shawn on 07/06/17.
 */
import {Injectable} from "@angular/core";
import {BehaviorSubject, Observable} from "rxjs/Rx";
import {RelayController} from "../models/relayController";
import {HttpClient} from "@angular/common/http";
import {StompService} from "./stomp.service";

@Injectable()
export class RelayService {

    private allRelayControllersUrl = '/api/relay-controller/all';
    private saveUrl = '/api/relay-controller/save';
    private updateUrl = '/api/relay-controller/update-value';

    private _relayControllers: BehaviorSubject<RelayController[]> = new BehaviorSubject(null);
    private relayControllers: Observable<RelayController[]> = this._relayControllers.asObservable();

    constructor(private http: HttpClient, private stompService: StompService) {
        this.fetchRelayControllers();
        this.stompService.subscribe("/relays", (data: RelayController) => this.updateRelayControllers(data));
    }

    updateRelayControllers(relayController: RelayController): void {
      let relayControllers: RelayController[] = this._relayControllers.getValue();
      let controllers: RelayController[] = [];
      let notFound: boolean = true;
      for(let currentController of relayControllers) {
        if (currentController.controllerId == relayController.controllerId) {
          controllers.push(relayController);
          notFound = false;
        } else {
          controllers.push(currentController);
        }
      }
      if (notFound) {
        controllers.push(relayController);
      }
      this._relayControllers.next(controllers);
    }

    fetchRelayControllers(): void {
        this.http.get(this.allRelayControllersUrl).subscribe((data: RelayController[]) => {
            this._relayControllers.next(data);
        }, error => console.log('Could not load relay controllers.'));
    }

    saveRelayController(relayController: RelayController): void {
        this.http.post(this.saveUrl, relayController).subscribe((data: RelayController[]) => {
            this._relayControllers.next(data);
        }, error => console.log('Could not load relay controllers.'));
    }

    updateRelayValue(relayController: RelayController): void {
        this.http.post(this.updateUrl, relayController).subscribe((data: RelayController[]) => {
            this._relayControllers.next(data);
        }, error => console.log('Could not load relay controllers.'));
    }

    getRelayControllers(): Observable<RelayController[]> {
        return this.relayControllers;
    }
}
