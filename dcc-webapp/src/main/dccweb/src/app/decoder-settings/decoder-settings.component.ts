import {Component, Input, OnInit} from '@angular/core';
import {DecoderSetting} from '../models/decoder-setting';

@Component({
  selector: 'app-decoder-setting',
  templateUrl: './decoder-settings.component.html',
  styleUrls: ['./decoder-settings.component.css']
})
export class DecoderSettingsComponent implements OnInit {

  @Input() decoderSetting: DecoderSetting;

  decoderSettingChanged(event: any): void {
    let newValue: number;
    const targetType: string = event.target.type;
    switch (targetType) {
      case 'number':
        newValue = event.target.value;
        break;
      case 'radio':
        newValue = event.target.value;
        break;
      case 'checkbox':
        newValue = event.target.checked ? 1 : 0;
        break;
    }
    this.decoderSetting.newValue = newValue;
  }

  isSet(): boolean {
    return this.decoderSetting.value > 0;
  }

  ngOnInit(): void {
  }

}
