import {Component, OnInit} from '@angular/core';
import {RegisterService} from '../register.service';
import {RegisterDetails} from '../register-details';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  registerDetails: RegisterDetails = new RegisterDetails();

  constructor(private registerService: RegisterService) {
  }

  register(): void {
    this.registerService.register(this.registerDetails);
  }

  ngOnInit(): void {
  }

}
