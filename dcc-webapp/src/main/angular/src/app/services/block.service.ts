/**
 * Created by shawn on 03/04/17.
 */
import {Injectable} from "@angular/core";
import {Headers, Http} from "@angular/http";
import {NotificationService} from "./notification.service";
import {Block} from "../models/block";
import {BehaviorSubject, Observable} from "rxjs/Rx";

@Injectable()
export class BlockService {

    private headers = new Headers({'Content-Type': 'application/json'});
    private blockUrl = '/api/block/all';
    private saveBlockUrl = '/api/block/save';
    private deleteBlockUrl = '/api/block/delete';
    private blockOccupiedUrl = '/api/block/occupied';

    private _blocks: BehaviorSubject<Block[]> = new BehaviorSubject(null);
    private blocks: Observable<Block[]> = this._blocks.asObservable();

    constructor(private http: Http, private notificationService: NotificationService) {
        this.fetchBlocks();
        this.notificationService.getBlockUpdates().subscribe(data => this.fetchBlocks());
    }

    fetchBlocks(): void {
        this.http.get(this.blockUrl).map(response => response.json()).subscribe(data => {
            this._blocks.next(data);
        }, error => console.log('Could not load blocks.'));
    }

    saveBlock(block: Block): void {
        this.http.post(this.saveBlockUrl, block).map(response => response.json()).subscribe(data => {
            this._blocks.next(data);
        }, error => console.log('Could not load blocks.'));
    }

    deleteBlock(block: Block): void {
        this.http.post(this.deleteBlockUrl, block).map(response => response.json()).subscribe(data => {
            this._blocks.next(data);
        }, error => console.log('Could not load blocks.'));
    }

    setBlockOccupancy(block: Block): void {
        this.http.post(this.blockOccupiedUrl, block).map(response => response.json()).subscribe(data => {
            this._blocks.next(data);
        }, error => console.log('Could not load blocks.'));
    }

    getBlocks(): Observable<Block[]> {
        return this.blocks;
    }
}