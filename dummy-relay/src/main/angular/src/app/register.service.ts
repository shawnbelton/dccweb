import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {RegisterDetails} from "./registerDetails";

@Injectable()
export class RegisterService {

  constructor(private http: HttpClient) {}

  register(registerDetails: RegisterDetails): void {
    this.http.put('/api/relay/register', registerDetails).subscribe();
  }
}
