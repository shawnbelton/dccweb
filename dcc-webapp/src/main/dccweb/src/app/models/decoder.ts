import {DccManufacturer} from './dcc-manufacturer';
import {DecoderFunction} from './decoder-function';
import {LinkedMacro} from './linked-macro';

export class Decoder {
  decoderId: number;
  shortAddress: number;
  longAddress: number;
  currentAddress: number;
  version: number;
  dccManufacturer: DccManufacturer;
  decoderFunctions: DecoderFunction[];
  linkedMacros: LinkedMacro[];
}
