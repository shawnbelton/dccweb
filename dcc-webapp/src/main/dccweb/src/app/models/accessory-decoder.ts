import {DecoderAccessoryType} from './decoder-accessory-type';

export class AccessoryDecoder {
  accessoryDecoderId: number;
  accessoryDecoderType: DecoderAccessoryType;
  address: number;
  name: string;
  currentValue: number;
  macroId: number;
}
