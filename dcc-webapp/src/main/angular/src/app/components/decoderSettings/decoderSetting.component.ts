/**
 * Created by shawn on 16/11/16.
 */
import {Component, Input} from "@angular/core";
import {DecoderSetting} from "../../models/decoderSetting";

@Component({
  moduleId: module.id,
  selector: 'decoderSetting',
  templateUrl: './../../html/decoder/decoderSetting.html'
})
export class DecoderSettingComponent {

  @Input("setting") decoderSetting: DecoderSetting;

}
