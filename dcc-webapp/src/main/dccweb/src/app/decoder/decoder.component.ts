import {Component, OnInit} from '@angular/core';
import {Decoder} from '../models/decoder';
import {DecoderSetting} from '../models/decoder-setting';
import {DecoderService} from '../decoder.service';

@Component({
  selector: 'app-decoder',
  templateUrl: './decoder.component.html',
  styleUrls: ['./decoder.component.css']
})
export class DecoderComponent implements OnInit {

  decoderSettings: DecoderSetting[];
  currentDecoder: Decoder;

  constructor(private decoderService: DecoderService) {
  }

  isNotEmptySettings(): boolean {
    let isNotEmpty = false;
    if (this.decoderSettings != null) {
      isNotEmpty = this.decoderSettings.length > 0;
    }
    return isNotEmpty;
  }

  writeCVs(): void {
    this.decoderService.writeCVs(this.decoderSettings);
  }

  setDecoderSettings(decoderSettingValues: DecoderSetting[]) {
    this.decoderSettings = decoderSettingValues;
  }

  getDecoderSettings(): void {
    this.decoderService.getDecoderSettings()
      .subscribe(decoderSettings => this.setDecoderSettings(decoderSettings));
  }

  ngOnInit(): void {
    this.getDecoderSettings();
  }

}
