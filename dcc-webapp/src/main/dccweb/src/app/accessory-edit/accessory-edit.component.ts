import {Component, OnDestroy, OnInit} from '@angular/core';
import {AccessoryDecoder} from '../models/accessory-decoder';
import {ActivatedRoute, Router} from '@angular/router';
import {AccessoryDecoderService} from '../accessory-decoder.service';

@Component({
  selector: 'app-accessory-edit',
  templateUrl: './accessory-edit.component.html',
  styleUrls: ['./accessory-edit.component.css']
})
export class AccessoryEditComponent implements OnInit, OnDestroy {

  accessory: AccessoryDecoder;
  sub: any;

  constructor(private accessoryService: AccessoryDecoderService,
              private route: ActivatedRoute,
              private router: Router) {
  }

  getAccessory(id: number): void {
    this.accessory = this.accessoryService.getAccessory(id);
  }

  saved(event: string): void {
    this.router.navigate(['/accessories']);
  }

  ngOnInit() {
    this.sub = this.route.params.subscribe(params => {
      this.getAccessory(+params['id']);
    });
  }

  ngOnDestroy(): void {
    this.sub.unsubscribe();
  }
}
