/**
 * Created by shawn on 03/04/17.
 */
import {Injectable} from "@angular/core";
import {Block} from "../models/block";
import {BehaviorSubject, Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {StompService} from "@stomp/ng2-stompjs";
import {Message} from '@stomp/stompjs';


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

  constructor(private http: HttpClient, private stompService: StompService) {
    this.fetchBlocks();
    this.stompService.subscribe('/blocks').subscribe(this.on_next);
  }

  public on_next = (message: Message) => {
   this.updateBlock(JSON.parse(message.body));
  }

  updateBlock(block: Block): void {
    let newBlocks: Block[] = [];
    let currentBlocks: Block[] = this._blocks.getValue();
    let notFound: boolean = true;
    for(let currentBlock of currentBlocks) {
      if (currentBlock.blockId == block.blockId) {
        newBlocks.push(block);
        notFound = false;
      } else {
        newBlocks.push(currentBlock);
      }
    }
    if (notFound) {
      newBlocks.push(block);
    }
    this._blocks.next(newBlocks);
    let currentBlock: Block = this._block.getValue();
    if (null != currentBlock) {
      if (currentBlock.blockId == block.blockId) {
        this._block.next(block);
      }
    }
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
