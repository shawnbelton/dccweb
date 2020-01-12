import {Injectable} from '@angular/core';
import {BehaviorSubject, Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import {StompService} from '@stomp/ng2-stompjs';
import {Block} from './models/block';
import {Message} from '@stomp/stompjs';

@Injectable({
  providedIn: 'root'
})
export class BlockService {
  private blockUrl = '/api/block/all';
  private saveBlockUrl = '/api/block/save';
  private deleteBlockUrl = '/api/block/delete';
  private blockOccupiedUrl = '/api/block/occupied';

  private _blocks: BehaviorSubject<Block[]> = new BehaviorSubject(null);
  private blocks: Observable<Block[]> = this._blocks.asObservable();

  constructor(private http: HttpClient, private stompService: StompService) {
    this.fetchBlocks();
    this.stompService.subscribe('/blocks').subscribe(this.on_next);
  }

  public on_next = (message: Message) => {
    this.updateBlock(JSON.parse(message.body));
  }

  updateBlock(block: Block): void {
    const newBlocks: Block[] = [];
    const currentBlocks: Block[] = this._blocks.getValue();
    let notFound = true;
    for (const curBlock of currentBlocks) {
      if (curBlock.blockId === block.blockId) {
        newBlocks.push(block);
        notFound = false;
      } else {
        newBlocks.push(curBlock);
      }
    }
    if (notFound) {
      newBlocks.push(block);
    }
    this._blocks.next(newBlocks);
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

  getBlock(blockId: string): Block {
    let block: Block = new Block();
    const currentBlocks: Block[] = this._blocks.getValue();
    for (const curBlock of currentBlocks) {
      if (blockId === curBlock.blockId) {
        block = curBlock;
      }
    }
    return block;
  }
}
