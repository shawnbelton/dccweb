import {Component, OnDestroy, OnInit} from '@angular/core';
import {DecoderService} from '../decoder.service';
import {Decoder} from '../models/decoder';

@Component({
  selector: 'app-decoder-read',
  templateUrl: './decoder-read.component.html',
  styleUrls: ['./decoder-read.component.css']
})
export class DecoderReadComponent implements OnInit, OnDestroy {

  decoder: Decoder;
  sub: any;

  constructor(private decoderService: DecoderService) {
  }

  readDecoder(): void {
    this.decoderService.readDecoder();
  }

  ngOnInit() {
    this.sub = this.decoderService.getDecoder().subscribe(decoder => this.decoder = decoder);
    this.readDecoder();
  }

  ngOnDestroy(): void {
    this.decoderService.clearDecoder();
    this.sub.unsubscribe();
  }

}
