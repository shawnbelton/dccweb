import {Component, OnInit} from '@angular/core';
import {Status} from '../models/status';
import {StatusService} from '../status.service';

@Component({
  selector: 'app-dcc-status',
  templateUrl: './status.component.html',
  styleUrls: ['./status.component.css']
})
export class StatusComponent implements OnInit {

  status: Status;

  constructor(private statusService: StatusService) { }

  getStatus(): void {
    this.statusService.getStatus().subscribe(status => this.status = status);
  }

  ngOnInit(): void {
    this.status = new Status();
    this.status.status = 'LOADING';
    this.getStatus();
  }

}
