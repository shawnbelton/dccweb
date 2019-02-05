import {Injectable} from '@angular/core';
import {BehaviorSubject, Observable} from 'rxjs';
import {RelayController} from './models/relay-controller';
import {HttpClient} from '@angular/common/http';
import {StompService} from '@stomp/ng2-stompjs';
import {Message} from '@stomp/stompjs';

@Injectable({
  providedIn: 'root'
})
export class RelayService {

  private allRelayControllersUrl = '/api/relay-controller/all';
  private saveUrl = '/api/relay-controller/save';
  private updateUrl = '/api/relay-controller/update-value';

  private _relayControllers: BehaviorSubject<RelayController[]> = new BehaviorSubject(null);
  private relayControllers: Observable<RelayController[]> = this._relayControllers.asObservable();

  constructor(private http: HttpClient, private stompService: StompService) {
    this.fetchRelayControllers();
    this.stompService.subscribe('/relays').subscribe(this.on_next);
  }

  public on_next = (message: Message) => {
    this.updateRelayControllers(JSON.parse(message.body));
  }

  updateRelayControllers(relayController: RelayController): void {
    const relayControllers: RelayController[] = this._relayControllers.getValue();
    const controllers: RelayController[] = [];
    let notFound = true;
    for (const currentController of relayControllers) {
      if (currentController.controllerId === relayController.controllerId) {
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

  getRelayController(controllerId: string): RelayController {
    let relayController: RelayController = new RelayController();
    for (const iRelayController of this._relayControllers.getValue()) {
      if (iRelayController.controllerId === controllerId) {
        relayController = iRelayController;
      }
    }
    return relayController;
  }

  getRelayControllers(): Observable<RelayController[]> {
    return this.relayControllers;
  }
}
