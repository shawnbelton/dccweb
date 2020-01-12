import {Injectable} from '@angular/core';
import {RegisterDetails} from './register-details';
import {HttpClient} from '@angular/common/http';

@Injectable()
export class RegisterService {

  constructor(private http: HttpClient) {
  }

  register(registerDetails: RegisterDetails): void {
    this.http.put('/api/relay/register', registerDetails).subscribe();
  }
}
