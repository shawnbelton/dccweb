import {Component, OnInit} from '@angular/core';
import {Info} from '../models/info';
import {InfoService} from '../info.service';
import * as moment from 'moment/moment';

@Component({
  selector: 'app-info',
  templateUrl: './info.component.html',
  styleUrls: ['./info.component.css']
})
export class InfoComponent implements OnInit {

  info: Info;

  constructor(private infoService: InfoService) {
  }

  getInfo(): void {
    this.infoService.getInfo().subscribe(info => this.setInfo(info));
  }

  setInfo(info: Info): void {
    this.info = info;
  }

  toDateString(time: number): string {
    const date: Date = new Date(time);
    return moment(date).format('h:mm:ssa Do MMMM YYYY');
  }

  ngOnInit(): void {
    this.getInfo();
  }
}
