import {Component} from "@angular/core";
import {RegisterDetails} from "./registerDetails";
import {RegisterService} from "./register.service";

@Component({
  selector: 'register-app',
  templateUrl: './html/register.html'
})
export class AppRegisterComponent {

  registerDetails: RegisterDetails = new RegisterDetails();

  constructor(private registerService: RegisterService) {}

  register(): void {
    this.registerService.register(this.registerDetails);
  }
}
