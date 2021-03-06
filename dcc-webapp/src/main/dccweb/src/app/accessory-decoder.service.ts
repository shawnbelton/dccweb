import {Injectable} from '@angular/core';
import {BehaviorSubject, Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import {StompService} from '@stomp/ng2-stompjs';
import {Message} from '@stomp/stompjs';
import {DecoderAccessoryType} from './models/decoder-accessory-type';
import {AccessoryDecoder} from './models/accessory-decoder';
import {AccessoryOperation} from './models/accessory-operation';

@Injectable({
  providedIn: 'root'
})
export class AccessoryDecoderService {
  private fetchAccessoryTypeUrl = '/api/accessory/decoder/type/all';
  private fetchAccessoriesUrl = '/api/accessory/decoder/all';
  private saveAccessoryUrl = '/api/accessory/decoder/save';
  private operateAccessoryUrl = '/api/accessory/decoder/operate';

  private _accessoryTypes: BehaviorSubject<DecoderAccessoryType[]> = new BehaviorSubject(null);
  private accessoryTypes: Observable<DecoderAccessoryType[]> = this._accessoryTypes.asObservable();

  private _accessories: BehaviorSubject<AccessoryDecoder[]> = new BehaviorSubject(null);
  private accessories: Observable<AccessoryDecoder[]> = this._accessories.asObservable();

  constructor(private http: HttpClient, private stompService: StompService) {
    this.fetchAccessoryTypes();
    this.fetchAccessories();
    this.stompService.subscribe('/accessory').subscribe(this.on_next);
  }

  public on_next = (message: Message) => {
    this.updateAccessory(JSON.parse(message.body));
  }

  updateAccessory(accessory: AccessoryDecoder): void {
    const currentAccessories: AccessoryDecoder[] = this._accessories.getValue();
    const accessories: AccessoryDecoder[] = [];
    let notFound = true;
    for (const curAccessory of currentAccessories) {
      if (curAccessory.accessoryDecoderId === accessory.accessoryDecoderId) {
        accessories.push(accessory);
        notFound = false;
      } else {
        accessories.push(curAccessory);
      }
    }
    if (notFound) {
      accessories.push(accessory);
    }
    this._accessories.next(accessories);
  }

  fetchAccessoryTypes(): void {
    this.http.get(this.fetchAccessoryTypeUrl).subscribe((data: DecoderAccessoryType[]) => {
      this._accessoryTypes.next(data);
    }, error => console.log('Could not load accessory types.'));
  }

  fetchAccessories(): void {
    this.http.get(this.fetchAccessoriesUrl).subscribe((data: AccessoryDecoder[]) => {
      this._accessories.next(data);
    }, error => console.log('Could not load accessories.'));
  }

  getAccessory(id: number): AccessoryDecoder {
    let accessory: AccessoryDecoder = new AccessoryDecoder();
    accessory.accessoryDecoderType = new DecoderAccessoryType();
    const currentAccessories: AccessoryDecoder[] = this._accessories.getValue();
    for (const curAccessory of currentAccessories) {
      if (curAccessory.accessoryDecoderId === id) {
        accessory = curAccessory;
      }
    }
    return accessory;
  }

  saveAccessory(accessory: AccessoryDecoder): void {
    this.http.post(this.saveAccessoryUrl, accessory).subscribe((data: AccessoryDecoder[]) => {
      this._accessories.next(data);
    }, error => console.log('Could not load accessories.'));
  }

  operateAccessory(accessoryOperation: AccessoryOperation): void {
    this.http.post(this.operateAccessoryUrl, accessoryOperation).subscribe((data: AccessoryDecoder[]) => {
      this._accessories.next(data);
    }, error => console.log('Could not operate accessory.'));
  }

  getAccessories(): Observable<AccessoryDecoder[]> {
    return this.accessories;
  }

  getAccessoryTypes(): Observable<DecoderAccessoryType[]> {
    return this.accessoryTypes;
  }

}
