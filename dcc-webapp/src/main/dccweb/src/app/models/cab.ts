import {Loco} from './loco';
import {CabFunction} from './cab-function';

export class Cab {
  loco: Loco;
  speed: number;
  direction: string;
  steps: string;
  cabFunctions: CabFunction[];
}
