/**
 * Created by shawn on 26/01/18.
 */
import {DecoderSettingOption} from "./decoderSettingOption";

export class DecoderSetting {
  id: string;
  name: string;
  type: string;
  value: number;
  decoderSettingOptions: DecoderSettingOption[];
}
