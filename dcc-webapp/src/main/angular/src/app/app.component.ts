import {Component} from "@angular/core";
import {Title} from "@angular/platform-browser";

@Component({
  selector: 'dcc-app',
  templateUrl: './html/design/header.html',
  styleUrls: ['./css/app.component.css']
})
export class AppComponent {

  constructor(private titleService: Title) {
    this.titleService.setTitle('Digital Command Control System');
  }

}
