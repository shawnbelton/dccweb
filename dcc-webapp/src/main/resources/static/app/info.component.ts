/**
 * Created by shawn on 12/01/17.
 */
import {OnInit, Component} from "@angular/core";
import {InfoService} from "./info.service";
import {Info} from "./info";
import * as moment from "moment";

@Component({
    moduleId: module.id,
    templateUrl: '/info/info.html',
    selector: 'dcc-info'
})
export class InfoComponent implements OnInit {

    info: Info;

    constructor(private infoService: InfoService) {}

    getInfo(): void {
        this.infoService.getInfo().subscribe(info => this.setInfo(info));
    }

    setInfo(info: Info): void {
        this.info = info;
    }

    toDateString(time: number): string {
        let date: Date = new Date(time);
        return moment(date).format('h:mm:ssa Do MMMM YYYY');
    }

    ngOnInit(): void {
        this.getInfo();
    }
}