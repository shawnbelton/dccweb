/**
 * Created by shawn on 26/11/16.
 */
import {Injectable} from "@angular/core";
import {Headers, Http} from "@angular/http";
import {Settings} from "../models/settings";
import {BehaviorSubject, Observable} from "rxjs/Rx";
import {InterfaceInfo} from "../models/interfaceInfo";

@Injectable()
export class SettingsService {

    private headers = new Headers({'Content-Type': 'application/json'});
    private settingsUrl = '/application/settings';
    private interfacesUrl = '/interfaces';

    private _settings: BehaviorSubject<Settings> = new BehaviorSubject(new Settings());
    private settings: Observable<Settings> = this._settings.asObservable();

    private _interfaces: BehaviorSubject<InterfaceInfo[]> = new BehaviorSubject([]);
    private interfaces: Observable<InterfaceInfo[]> = this._interfaces.asObservable();

    constructor(private http: Http) {
        this.loadSettings();
        this.loadInterfaces();
    }

    loadSettings(): void {
        this.http.get(this.settingsUrl).map(response => response.json()).subscribe(data => {
            this._settings.next(data);
        }, error => console.log('Could not load settings.'));
    }

    loadInterfaces(): void {
        this.http.get(this.interfacesUrl).map(response => response.json()).subscribe(data => {
            this._interfaces.next(data);
        }, error => console.log('Could not load interfaces.'));
    }

    saveSettings(settings: Settings): void {
        this.http.post(this.settingsUrl, settings).map(response => response.json()).subscribe(data => {
           this._settings.next(data);
        }, error => console.log('Could not load settings.'));
    }

    getSettings(): Observable<Settings> {
        return this.settings;
    }

    getInterfaces(): Observable<InterfaceInfo[]> {
        return this.interfaces;
    }
}