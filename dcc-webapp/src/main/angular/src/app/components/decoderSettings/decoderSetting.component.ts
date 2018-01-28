/**
 * Created by shawn on 16/11/16.
 */
import {Component, Input} from "@angular/core";
import {DecoderSetting} from "../../models/decoderSetting";

@Component({
  moduleId: module.id,
  selector: 'decoderSetting',
  template: '<div>decoderSetting</div>'
})
export class DecoderSettingComponent {

  @Input("setting") decoderSetting: DecoderSetting;

}
