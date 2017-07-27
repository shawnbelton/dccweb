/**
 * Created by shawn on 23/11/16.
 */
import {Loco} from "./loco";
import {CabFunction} from "./cabFunction";

export class Cab {
    loco: Loco;
    speed: number;
    direction: string;
    steps: string;
    cabFunctions: CabFunction[];
}
