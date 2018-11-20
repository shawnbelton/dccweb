import {Injectable} from '@angular/core';
import {BehaviorSubject, Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import {Settings} from './models/settings';
import {InterfaceInfo} from './models/interface-info';

@Injectable({
  providedIn: 'root'
})
export class SettingsService {

  private settingsUrl = '/api/application/settings';
  private interfacesUrl = '/api/interface/all';

  private _settings: BehaviorSubject<Settings> = new BehaviorSubject(new Settings());
  private settings: Observable<Settings> = this._settings.asObservable();

  private _interfaces: BehaviorSubject<InterfaceInfo[]> = new BehaviorSubject([]);
  private interfaces: Observable<InterfaceInfo[]> = this._interfaces.asObservable();

  constructor(private http: HttpClient) {
    this.loadSettings();
    this.loadInterfaces();
  }

  loadSettings(): void {
    this.http.get(this.settingsUrl).subscribe((data: Settings) => {
      this._settings.next(data);
    }, error => console.log('Could not load settings.'));
  }

  loadInterfaces(): void {
    this.http.get(this.interfacesUrl).subscribe((data: InterfaceInfo[]) => {
      this._interfaces.next(data);
    }, error => console.log('Could not load interfaces.'));
  }

  saveSettings(settings: Settings): void {
    this.http.post(this.settingsUrl, settings).subscribe((data: Settings) => {
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
