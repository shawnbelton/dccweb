import {Component, OnDestroy, OnInit} from '@angular/core';
import {DecoderService} from '../decoder.service';
import {ActivatedRoute} from '@angular/router';
import {Decoder} from '../models/decoder';

@Component({
  selector: 'app-decoder-edit',
  templateUrl: './decoder-edit.component.html',
  styleUrls: ['./decoder-edit.component.css']
})
export class DecoderEditComponent implements OnInit, OnDestroy {

  decoder: Decoder;
  sub: any;

  constructor(private decoderService: DecoderService,
              private route: ActivatedRoute) { }

  getDecoder(id: number): void {
    this.decoder = this.decoderService.getDecoderById(id);
  }

  ngOnInit(): void {
    this.sub = this.route.params.subscribe(params => {
      this.getDecoder(+params['id']);
    });
  }

  ngOnDestroy(): void {
    this.sub.unsubscribe();
  }
}
