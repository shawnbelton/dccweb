/**
 * Created by shawn on 03/04/17.
 */
import {Injectable} from "@angular/core";
import {NotificationService} from "./notification.service";
import {Block} from "../models/block";
import {BehaviorSubject, Observable} from "rxjs/Rx";
import {HttpClient} from "@angular/common/http";

@Injectable()
export class BlockService {

  private blockUrl = '/api/block/all';
  private saveBlockUrl = '/api/block/save';
  private deleteBlockUrl = '/api/block/delete';
  private blockOccupiedUrl = '/api/block/occupied';

  private _blocks: BehaviorSubject<Block[]> = new BehaviorSubject(null);
  private blocks: Observable<Block[]> = this._blocks.asObservable();

  private _block: BehaviorSubject<Block> = new BehaviorSubject(null);
  private block: Observable<Block> = this._block.asObservable();

  constructor(private http: HttpClient, private notificationService: NotificationService) {
    this.fetchBlocks();
    this.notificationService.getBlockUpdates().subscribe(data => this.fetchBlocks());
  }

  fetchBlocks(): void {
    this.http.get(this.blockUrl).subscribe((data: Block[]) => {
      this._blocks.next(data);
    }, error => console.log('Could not load blocks.'));
  }

  saveBlock(block: Block): void {
    this.http.post(this.saveBlockUrl, block).subscribe((data: Block[]) => {
      this._blocks.next(data);
    }, error => console.log('Could not load blocks.'));
  }

  deleteBlock(block: Block): void {
    this.http.post(this.deleteBlockUrl, block).subscribe((data: Block[]) => {
      this._blocks.next(data);
    }, error => console.log('Could not load blocks.'));
  }

  setBlockOccupancy(block: Block): void {
    this.http.post(this.blockOccupiedUrl, block).subscribe((data: Block[]) => {
      this._blocks.next(data);
    }, error => console.log('Could not load blocks.'));
  }

  getBlocks(): Observable<Block[]> {
    return this.blocks;
  }

  setBlock(block: Block): void {
    this._block.next(block);
  }

  getBlock(): Observable<Block> {
    return this.block;
  }
}
