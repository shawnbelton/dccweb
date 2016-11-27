/**
 * Created by shawn on 26/11/16.
 */
import {Injectable} from "@angular/core";
import {Headers, Http} from "@angular/http";
import {Settings} from "./settings";
import {Observable, BehaviorSubject} from "rxjs/Rx";

@Injectable()
export class SettingsService {

    private headers = new Headers({'Content-Type': 'application/json'});
    private settingsUrl = '/application/settings';

    private _settings: BehaviorSubject<Settings> = new BehaviorSubject(new Settings());
    private settings: Observable<Settings> = this._settings.asObservable();

    constructor(private http: Http) {
        this.loadSettings();
    }

    loadSettings(): void {
        this.http.get(this.settingsUrl).map(response => response.json()).subscribe(data => {
            this._settings.next(data);
        }, error => console.log('Could not load settings.'));
    }

    saveSettings(settings: Settings): void {
        this.http.post(this.settingsUrl, settings).map(response => response.json()).subscribe(data => {
           this._settings.next(data);
        }, error => console.log('Could not load settings.'));
    }

    getSettings(): Observable<Settings> {
        return this.settings;
    }
}