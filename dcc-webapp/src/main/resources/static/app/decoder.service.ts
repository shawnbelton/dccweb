/**
 * Created by shawn on 19/11/16.
 */
import {Injectable} from "@angular/core";
import {Headers, Http} from "@angular/http";
import {Observable, BehaviorSubject} from "rxjs/Rx";
import "rxjs/add/operator/toPromise";
import {Decoder} from "./decoder";
import {DecoderFunction} from "./decoderFunction";
import {LinkedMacro} from "./linked.macro";

@Injectable()
export class DecoderService {

    private headers = new Headers({'Content-Type': 'application/json'});
    private decodersUrl = '/decoders/all';
    private readDecoderUrl = '/decoders/read';
    private fetchDecoderUrl = '/decoders/byId/';
    private addFunctionUrl = '/decoders/function/add';
    private deleteFunctionUrl = '/decoders/function/delete';
    private linkMacroUrl = '/decoders/macro/link';
    private unlinkMacroUrl = '/decoders/macro/unlink';

    private _decoders: BehaviorSubject<Decoder[]> = new BehaviorSubject([]);
    private decoders: Observable<Decoder[]> = this._decoders.asObservable();

    private _decoder: BehaviorSubject<Decoder> = new BehaviorSubject(null);
    private decoder: Observable<Decoder> = this._decoder.asObservable();

    constructor(private http: Http) {
        this.fetchDecoders();
    }

    fetchDecoders(): void {
        this.http.get(this.decodersUrl).map(response => response.json()).subscribe(data => {
            this._decoders.next(data);
        }, error => console.log('Could not load decoders.'));
    }

    fetchDecoder(decoderId: number): void {
        this.http.get(this.fetchDecoderUrl + decoderId).map(response => response.json()).subscribe(data => {
            this._decoder.next(data);
            this.fetchDecoders();
        }, error => console.log('Could not load decoder.'));
    }

    readDecoder(): void {
        this.http.get(this.readDecoderUrl).map(response => response.json()).subscribe(data => {
            this._decoder.next(data);
            this.fetchDecoders();
        }, error => console.log('Could not load decoder.'));
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
}