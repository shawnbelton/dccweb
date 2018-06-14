import {Injectable} from "@angular/core";
import {StompService} from "@stomp/ng2-stompjs";
import {Message} from '@stomp/stompjs';
import {Observable} from "rxjs/Rx";
import {BehaviorSubject} from "rxjs/Rx";

@Injectable()
export class RelayService {

  private _values: BehaviorSubject<number> = new BehaviorSubject(0);
  private values: Observable<number> = this._values.asObservable();

  constructor(private stompService: StompService) {
    this.stompService.subscribe('/relays').map((message: Message) => {
      return message.body;
    }).subscribe((data: string) => {
      this.updateValue(data);
    });
  }

  updateValue(value: string): void {
    this._values.next(Number(value.trim()));
  }

  getValues(): Observable<number> {
    return this.values;
  }
}
