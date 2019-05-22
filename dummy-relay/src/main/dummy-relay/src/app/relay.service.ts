import {Injectable} from '@angular/core';
import {BehaviorSubject, Observable} from 'rxjs';
import {StompService} from '@stomp/ng2-stompjs';
import {Message} from '@stomp/stompjs';

@Injectable()
export class RelayService {

  private _values: BehaviorSubject<number> = new BehaviorSubject(0);
  private values: Observable<number> = this._values.asObservable();

  constructor(private stompService: StompService) {
    this.stompService.subscribe('/relays').subscribe(this.on_next);
  }

  public on_next = (message: Message) => {
    this.updateValue(JSON.parse(message.body));
  };

  updateValue(value: string): void {
    this._values.next(Number(value));
  }

  getValues(): Observable<number> {
    return this.values;
  }
}
