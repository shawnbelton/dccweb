import {DecoderSettingOption} from './decoder-setting-option';

export class DecoderSetting {
  id: string;
  name: string;
  type: string;
  value: number;
  newValue: number;
  decoderSettingOptions: DecoderSettingOption[];
}
