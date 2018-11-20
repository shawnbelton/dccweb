import {Injectable} from '@angular/core';
import {BehaviorSubject, Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import {Loco} from './models/loco';

@Injectable({
  providedIn: 'root'
})
export class LocoService {

  private locosUrl = '/api/locos/all';
  private saveUrl = '/api/locos/save';

  private _locos: BehaviorSubject<Loco[]> = new BehaviorSubject([]);
  private locos: Observable<Loco[]> = this._locos.asObservable();

  constructor(private http: HttpClient) {
    this.loadInitialData();
  }

  loadInitialData(): void {
    this.http.get(this.locosUrl).subscribe((data: Loco[]) => {
      this._locos.next(data);
    }, error => console.log('Could not load locos.'));
  }

  saveLoco(loco: Loco): void {
    this.http.post(this.saveUrl, loco).subscribe((data: Loco[]) => {
      this._locos.next(data);
    }, error => console.log('Could not load locos.'));
  }

  getLocos(): Observable<Loco[]> {
    return this.locos;
  }
}
