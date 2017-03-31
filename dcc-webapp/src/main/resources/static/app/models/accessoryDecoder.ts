/**
 * Created by shawn on 24/02/17.
 */
import {DecoderAccessoryType} from "./decoderAccessoryType";
export class AccessoryDecoder {
    accessoryDecoderId: number;
    accessoryDecoderType: DecoderAccessoryType;
    address: number;
    name: string;
    currentValue: number;
}