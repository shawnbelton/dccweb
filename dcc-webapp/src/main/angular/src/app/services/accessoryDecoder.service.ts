/**
 * Created by shawn on 24/02/17.
 */
import {Injectable} from "@angular/core";
import {DecoderAccessoryType} from "../models/decoderAccessoryType";
import {BehaviorSubject, Observable} from "rxjs/Rx";
import {AccessoryDecoder} from "../models/accessoryDecoder";
import {AccessoryOperation} from "../models/accessoryOperation";
import {NotificationService} from "./notification.service";
import {HttpClient} from "@angular/common/http";

@Injectable()
export class AccessoryDecoderService {

    private fetchAccessoryTypeUrl = '/api/accessory/decoder/type/all';
    private fetchAccessoriesUrl = '/api/accessory/decoder/all';
    private saveAccessoryUrl = '/api/accessory/decoder/save';
    private operateAccessoryUrl = '/api/accessory/decoder/operate';

    private _accessoryTypes: BehaviorSubject<DecoderAccessoryType[]> = new BehaviorSubject(null);
    private accessoryTypes: Observable<DecoderAccessoryType[]> = this._accessoryTypes.asObservable();

    private _accessories: BehaviorSubject<AccessoryDecoder[]> = new BehaviorSubject(null);
    private accessories: Observable<AccessoryDecoder[]> = this._accessories.asObservable();

    private _accessory: BehaviorSubject<AccessoryDecoder> = new BehaviorSubject(null);
    private accessory: Observable<AccessoryDecoder> = this._accessory.asObservable();

    constructor(private http: HttpClient, private notificationService: NotificationService) {
        this.fetchAccessoryTypes();
        this.fetchAccessories();
        this.notificationService.getAccessoryUpdates().subscribe(data => this.fetchAccessories());
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

    saveAccessory(accessory: AccessoryDecoder): void {
        this.http.post(this.saveAccessoryUrl, accessory).subscribe((data: AccessoryDecoder[]) => {
            this._accessories.next(data);
        }, error => console.log('Could not load accessories.'));
    }

    operateAccessory(accessoryOperation: AccessoryOperation): void {
        let retval: boolean = false;
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

    getAccessory(): Observable<AccessoryDecoder> {
        return this.accessory;
    }
}
