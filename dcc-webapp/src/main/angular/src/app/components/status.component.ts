/**
 * Created by shawn on 16/11/16.
 */
import {Component, OnInit} from '@angular/core';
import {Status} from '../models/status';
import {StatusService} from '../status.service';


@Component({
    moduleId: module.id,
    selector: 'app-dcc-status',
    template: '<span class="navbar-text">Status: {{status.status}}</span>'
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
        this.getStatus()
    }
}
