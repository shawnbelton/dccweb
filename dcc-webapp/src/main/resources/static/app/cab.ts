/**
 * Created by shawn on 23/11/16.
 */
import {Train} from "./train";
import {CabFunction} from "./cabFunction";

export class Cab {
    train: Train;
    speed: number;
    direction: string;
    steps: string;
    cabFunctions: CabFunction[];
}
