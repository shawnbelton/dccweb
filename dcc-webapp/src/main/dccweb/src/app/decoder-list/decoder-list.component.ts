import { Component, OnInit } from '@angular/core';
import {DecoderService} from "../decoder.service";
import {Decoder} from "../models/decoder";

@Component({
  selector: 'app-decoder-list',
  templateUrl: './decoder-list.component.html',
  styleUrls: ['./decoder-list.component.css']
})
export class DecoderListComponent implements OnInit {

  decoders: Decoder[];

  constructor(private decoderService: DecoderService) { }

  getDecoders(): void {
    this.decoderService
      .getDecoders().subscribe(decoders => this.decoders = decoders);
  }

  ngOnInit() {
    this.getDecoders();
  }

}
