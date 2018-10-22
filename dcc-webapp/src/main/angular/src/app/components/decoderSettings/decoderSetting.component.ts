/**
 * Created by shawn on 16/11/16.
 */
import {Component, Input} from '@angular/core';
import {DecoderSetting} from '../../models/decoderSetting';

@Component({
  moduleId: module.id,
  selector: 'decoderSetting',
  templateUrl: './../../html/decoder/decoderSetting.html'
})
export class DecoderSettingComponent {

  @Input('setting') decoderSetting: DecoderSetting;

  decoderSettingChanged(event: any): void {
    let newValue: number;
    const targetType: string = event.target.type;
    switch(targetType) {
      case 'number':
        newValue = event.target.value;
        break;
      case 'radio':
        newValue = event.target.value;
        break;
      case 'checkbox':
        newValue = event.target.checked?1:0;
        break;
    }
    this.decoderSetting.newValue = newValue;
  }

}
