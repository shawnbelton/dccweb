import {DecoderAccessoryTypeOperation} from './decoder-accessory-type-operation';

export class DecoderAccessoryType {
  decoderTypeId: number;
  decoderType: string;
  decoderTypeCode: string;
  decoderTypeOperations: DecoderAccessoryTypeOperation[];
}
