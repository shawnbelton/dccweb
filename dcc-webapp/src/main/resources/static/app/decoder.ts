import {DccManufacturer} from "./dccManufacturer";
import {DecoderFunction} from "./decoderFunction";
/**
 * Created by shawn on 19/11/16.
 */
export class Decoder {
    decoderId: number;
    shortAddress: number;
    longAddress: number;
    currentAddress: number;
    version: number;
    dccManufacturer: DccManufacturer;
    decoderFunctions: DecoderFunction[];
}
