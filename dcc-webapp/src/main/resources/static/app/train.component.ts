/**
 * Created by shawn on 16/11/16.
 */
import {Component, OnInit} from "@angular/core";
import {Train} from "./train";
import {TrainService} from "./train.service";
import {DecoderService} from "./decoder.service";
import {Decoder} from "./decoder";
import {CabService} from "./cab.service";

@Component({
    moduleId: module.id,
    templateUrl: '/train/train.html'
})
export class TrainComponent implements OnInit {

    trains: Train[];
    editTrain: Train;
    decoders: Decoder[];
    mode: boolean;

    constructor(private trainService: TrainService,
        private decoderService: DecoderService,
        private cabService: CabService) {
        this.mode = false;
    }

    getTrains(): void {
        this.trainService.getTrains().subscribe(trains => this.trains = trains);
    }

    getDecoders(): void {
        this.decoderService.getDecoders().subscribe(decoders => this.decoders = decoders);
    }

    configure(train: Train): void {
        train.showConfig = !train.showConfig;
        if (train.decoder == null) {
            var decoder = new Decoder();
            train.decoder = decoder;
        }
    }

    assignDecoder(train: Train): void {
        this.trainService.saveTrain(train);
        train.showConfig = false;
    }

    newTrain(): void {
        this.mode = true;
        this.editTrain = new Train();
    }

    startEditTrain(train: Train): void {
        this.mode = true;
        this.editTrain = train;
    }

    cancelEdit(): void {
        this.mode = false;
        this.editTrain = null;
    }

    saveTrain(): void {
        this.trainService.saveTrain(this.editTrain);
        this.mode = false;
        this.editTrain = null;
    }

    driveTrain(train: Train): void {
        this.cabService.setTrain(train);
    }

    ngOnInit(): void {
        this.getTrains();
        this.getDecoders();
    }
}