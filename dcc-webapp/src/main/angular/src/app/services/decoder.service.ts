/**
 * Created by shawn on 19/11/16.
 */
import {Injectable} from '@angular/core';
import {BehaviorSubject, Observable} from 'rxjs';
import {Decoder} from '../models/decoder';
import {DecoderFunction} from '../models/decoderFunction';
import {LinkedMacro} from '../models/linked.macro';
import {DecoderSetting} from '../models/decoderSetting';
import {HttpClient} from '@angular/common/http';
import {StompService} from '@stomp/ng2-stompjs';
import {Message} from '@stomp/stompjs';

@Injectable()
export class DecoderService {

  private decodersUrl = '/api/decoders/all';
  private readDecoderUrl = '/api/decoders/read';
  private readFullDecoderUrl = '/api/decoders/read/full';
  private writeDecoderUrl = '/api/decoders/write';
  private fetchDecoderUrl = '/api/decoders/byId/';
  private addFunctionUrl = '/api/decoders/function/add';
  private deleteFunctionUrl = '/api/decoders/function/delete';
  private linkMacroUrl = '/api/decoders/macro/link';
  private unlinkMacroUrl = '/api/decoders/macro/unlink';

  private _decoders: BehaviorSubject<Decoder[]> = new BehaviorSubject([]);
  private decoders: Observable<Decoder[]> = this._decoders.asObservable();

  private _decoder: BehaviorSubject<Decoder> = new BehaviorSubject(null);
  private decoder: Observable<Decoder> = this._decoder.asObservable();

  private _decoderSettings: BehaviorSubject<DecoderSetting[]> = new BehaviorSubject<DecoderSetting[]>(null);
  private decoderSettings: Observable<DecoderSetting[]> = this._decoderSettings.asObservable();

  constructor(private http: HttpClient, private stompService: StompService) {
    this.fetchDecoders();
    this.stompService.subscribe('/decoder').subscribe(this.on_next);
  }

  public on_next = (message: Message) => {
    this.updateDecoders(JSON.parse(message.body));
  }

  updateDecoders(decoder: Decoder): void {
    const decoders: Decoder[] = this._decoders.getValue();
    const newDecoders: Decoder[] = [];
    let notFound = true;
    for (const currentDecoder of decoders) {
      if (decoder.decoderId === currentDecoder.decoderId) {
        newDecoders.push(decoder);
        notFound = false;
      } else {
        newDecoders.push(currentDecoder);
      }
    }
    if (notFound) {
      newDecoders.push(decoder);
    }
    this._decoders.next(newDecoders);
    const currentDecoder: Decoder = this._decoder.getValue();
    if (null != currentDecoder) {
      if (decoder.decoderId === currentDecoder.decoderId) {
        this._decoder.next(decoder);
      }
    }
  }

  fetchDecoders(): void {
    this.http.get(this.decodersUrl).subscribe((data: Decoder[]) => {
      this._decoders.next(data);
    }, error => console.log('Could not load decoders.'));
  }

  fetchDecoder(decoderId: number): void {
    this.http.get(this.fetchDecoderUrl + decoderId).subscribe((data: Decoder) => {
      this._decoder.next(data);
      this._decoderSettings.next(null);
      this.fetchDecoders();
    }, error => console.log('Could not load decoder.'));
  }

  readDecoder(): void {
    this.http.get(this.readDecoderUrl).subscribe((data: Decoder) => {
      this._decoder.next(data);
      this._decoderSettings.next(null);
      this.fetchDecoders();
    }, error => console.log('Could not load decoder.'));
  }

  readFullDecoder(): void {
    this.http.get(this.readFullDecoderUrl).subscribe((data: DecoderSetting[]) => {
      this._decoderSettings.next(data);
      this._decoder.next(null);
    }, error => console.log('Could not load decoder settings.'));
  }

  writeCVs(decoderSettings: DecoderSetting[]) {
    this.http.post(this.writeDecoderUrl, decoderSettings).subscribe(data => {
      this._decoderSettings.next(null);
    });
  }

  addDecoderFunction(decoderFunction: DecoderFunction): void {
    this.http.post(this.addFunctionUrl, decoderFunction).subscribe((data: Decoder) => {
      this._decoder.next(data);
    }, error => console.log('Could not load decoder.'));
  }

  deleteDecoderFunction(decoderFunction: DecoderFunction): void {
    this.http.post(this.deleteFunctionUrl, decoderFunction).subscribe((data: Decoder) => {
      this._decoder.next(data);
    }, error => console.log('Could not load decoder.'));
  }

  linkMacro(linkedMacro: LinkedMacro): void {
    this.http.post(this.linkMacroUrl, linkedMacro).subscribe((data: Decoder) => {
      this._decoder.next(data);
    }, error => console.log('Could not load decoder.'));
  }

  unlinkMacro(linkedMacro: LinkedMacro): void {
    this.http.post(this.unlinkMacroUrl, linkedMacro).subscribe((data: Decoder) => {
      this._decoder.next(data);
    }, error => console.log('Could not load decoder.'));
  }

  getDecoder(): Observable<Decoder> {
    return this.decoder;
  }

  getDecoders(): Observable<Decoder[]> {
    return this.decoders;
  }

  getDecoderSettings(): Observable<DecoderSetting[]> {
    return this.decoderSettings;
  }
}
