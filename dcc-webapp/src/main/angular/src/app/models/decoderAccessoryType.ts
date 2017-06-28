/**
 * Created by shawn on 24/02/17.
 */
import {DecoderAccessoryTypeOperation} from "./decoderAccessoryTypeOpertation";
export class DecoderAccessoryType {
    decoderTypeId: number;
    decoderType: string;
    decoderTypeCode: string;
    decoderTypeOperations: DecoderAccessoryTypeOperation[];
}