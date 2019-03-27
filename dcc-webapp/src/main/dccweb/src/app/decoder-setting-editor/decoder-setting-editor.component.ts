import {Component, OnDestroy, OnInit} from '@angular/core';
import {DecoderSetting} from '../models/decoder-setting';
import {DecoderService} from '../decoder.service';
import {Router} from "@angular/router";

@Component({
  selector: 'app-decoder-setting-editor',
  templateUrl: './decoder-setting-editor.component.html',
  styleUrls: ['./decoder-setting-editor.component.css']
})
export class DecoderSettingEditorComponent implements OnInit, OnDestroy {

  decoderSettings: DecoderSetting[];
  sub: any;

  constructor(private decoderService: DecoderService,
              private router: Router) {
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
    this.router.navigate(['/decoders']);
  }

  setDecoderSettings(decoderSettingValues: DecoderSetting[]) {
    this.decoderSettings = decoderSettingValues;
  }

  getDecoderSettings(): void {
    this.sub = this.decoderService.getDecoderSettings()
      .subscribe(decoderSettings => this.setDecoderSettings(decoderSettings));
  }

  ngOnInit(): void {
    this.getDecoderSettings();
    this.decoderService.readFullDecoder();
  }

  ngOnDestroy(): void {
    this.decoderService.clearDecoder();
    this.sub.unsubscribe();
  }


}
