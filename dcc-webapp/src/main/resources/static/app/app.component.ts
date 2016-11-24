/**
 * Created by shawn on 16/11/16.
 */
import {Component} from "@angular/core";
import {Title} from "@angular/platform-browser";
@Component({
    moduleId: module.id,
    selector: 'dcc-app',
    templateUrl: '/design/header.html'
})
export class AppComponent {

    constructor(private titleService: Title) {
        this.titleService.setTitle('Digital Command Control System');
    }

}
