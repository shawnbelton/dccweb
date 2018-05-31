/**
 * Created by shawn on 23/11/16.
 */
import {Injectable} from "@angular/core";
import {Cab} from "../models/cab";
import {BehaviorSubject, Observable} from "rxjs/Rx";
import {Loco} from "../models/loco";
import {HttpClient} from "@angular/common/http";
import {StompService} from "@stomp/ng2-stompjs";
import {Message} from '@stomp/stompjs';

@Injectable()
export class CabService {

    private fetchCabUrl = '/api/locos/cab';
    private updateCabUrl = '/api/locos/cab/update';
    private updateCabFunctionUrl = '/api/locos/cab/updateFunction';

    private _cab: BehaviorSubject<Cab> = new BehaviorSubject(null);
    private cab: Observable<Cab> = this._cab.asObservable();
    private response: boolean;

    constructor(private http: HttpClient, private stompService: StompService) {
      this.stompService.subscribe('/cab').map((message: Message) => {
        return message.body;
      }).subscribe((data: string) => {
        this.cabUpdate(JSON.parse(data));
      });
    }

    cabUpdate(cab: Cab): void {
      let current: Cab = this._cab.getValue();
      if (null != current) {
        if (cab.loco.locoId == current.loco.locoId) {
          current.cabFunctions = cab.cabFunctions;
          current.direction = cab.direction;
          current.speed = cab.speed;
          current.steps = cab.steps;
          this._cab.next(current);
        }
      }
    }

    updateCab(cab: Cab): void {
        this.http.post(this.updateCabUrl, cab).subscribe((data: boolean) => this.response = data);
    }

    updateCabFunction(cab: Cab): void {
        this.http.post(this.updateCabFunctionUrl, cab).subscribe((data: boolean) => this.response = data);
    }

    setLoco(loco: Loco): void {
        this.http.post(this.fetchCabUrl, loco).subscribe((data: Cab) => {
            this._cab.next(data);
        }, error => console.log('Could not load cab.'));
    }

    getCab(): Observable<Cab> {
        return this.cab;
    }
}
