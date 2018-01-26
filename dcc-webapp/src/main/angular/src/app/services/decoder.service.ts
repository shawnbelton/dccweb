/**
 * Created by shawn on 19/11/16.
 */
import {Injectable} from "@angular/core";
import {Headers, Http} from "@angular/http";
import {BehaviorSubject, Observable} from "rxjs/Rx";
import {Decoder} from "../models/decoder";
import {DecoderFunction} from "../models/decoderFunction";
import {LinkedMacro} from "../models/linked.macro";
import {DecoderSetting} from "../models/decoderSetting";
import {NotificationService} from "./notification.service";

@Injectable()
export class DecoderService {

    private headers = new Headers({'Content-Type': 'application/json'});
    private decodersUrl = '/api/decoders/all';
    private readDecoderUrl = '/api/decoders/read';
    private readFullDecoderUrl = '/api/decoders/read/full';
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

    constructor(private http: Http, private notificationService: NotificationService) {
        this.fetchDecoders();
        this.notificationService.getDecoderUpdates().subscribe(decoders => this.fetchDecoders());
    }

    fetchDecoders(): void {
        this.http.get(this.decodersUrl).map(response => response.json()).subscribe(data => {
            this._decoders.next(data);
        }, error => console.log('Could not load decoders.'));
    }

    fetchDecoder(decoderId: number): void {
        this.http.get(this.fetchDecoderUrl + decoderId).map(response => response.json()).subscribe(data => {
            this._decoder.next(data);
            this._decoderSettings.next(null);
            this.fetchDecoders();
        }, error => console.log('Could not load decoder.'));
    }

    readDecoder(): void {
        this.http.get(this.readDecoderUrl).map(response => response.json()).subscribe(data => {
            this._decoder.next(data);
            this._decoderSettings.next(null);
            this.fetchDecoders();
        }, error => console.log('Could not load decoder.'));
    }

    readFullDecoder(): void {
      this.http.get(this.readFullDecoderUrl).map(response => response.json()).subscribe(data => {
        this._decoderSettings.next(data);
        this._decoder.next(null);
      }, error => console.log('Could not load decoder settings.'));
    }

    addDecoderFunction(decoderFunction: DecoderFunction): void {
        this.http.post(this.addFunctionUrl, decoderFunction).map(response => response.json()).subscribe(data => {
            this._decoder.next(data);
        }, error => console.log('Could not load decoder.'));
    }

    deleteDecoderFunction(decoderFunction: DecoderFunction): void {
        this.http.post(this.deleteFunctionUrl, decoderFunction).map(response => response.json()).subscribe(data => {
            this._decoder.next(data);
        }, error => console.log('Could not load decoder.'));
    }

    linkMacro(linkedMacro: LinkedMacro): void {
        this.http.post(this.linkMacroUrl, linkedMacro).map(response => response.json()).subscribe(data => {
            this._decoder.next(data);
        }, error => console.log('Could not load decoder.'));
    }

    unlinkMacro(linkedMacro: LinkedMacro): void {
        this.http.post(this.unlinkMacroUrl, linkedMacro).map(response => response.json()).subscribe(data => {
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
