"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
Object.defineProperty(exports, "__esModule", { value: true });
/**
 * Created by shawn on 16/11/16.
 */
const core_1 = require("@angular/core");
const train_1 = require("../models/train");
const train_service_1 = require("../services/train.service");
const decoder_service_1 = require("../services/decoder.service");
const decoder_1 = require("../models/decoder");
const cab_service_1 = require("../services/cab.service");
let TrainComponent = class TrainComponent {
    constructor(trainService, decoderService, cabService) {
        this.trainService = trainService;
        this.decoderService = decoderService;
        this.cabService = cabService;
        this.mode = false;
    }
    getTrains() {
        this.trainService.getTrains().subscribe(trains => this.trains = trains);
    }
    getDecoders() {
        this.decoderService.getDecoders().subscribe(decoders => this.decoders = decoders);
    }
    configure(train) {
        train.showConfig = !train.showConfig;
        if (train.decoder == null) {
            train.decoder = new decoder_1.Decoder();
        }
    }
    assignDecoder(train) {
        this.trainService.saveTrain(train);
        train.showConfig = false;
    }
    newTrain() {
        this.mode = true;
        this.editTrain = new train_1.Train();
    }
    startEditTrain(train) {
        this.mode = true;
        this.editTrain = train;
    }
    cancelEdit() {
        this.mode = false;
        this.editTrain = null;
    }
    saveTrain() {
        this.trainService.saveTrain(this.editTrain);
        this.mode = false;
        this.editTrain = null;
    }
    driveTrain(train) {
        this.cabService.setTrain(train);
    }
    ngOnInit() {
        this.getTrains();
        this.getDecoders();
    }
};
TrainComponent = __decorate([
    core_1.Component({
        moduleId: module.id,
        templateUrl: '../../train/train.html'
    }),
    __metadata("design:paramtypes", [train_service_1.TrainService,
        decoder_service_1.DecoderService,
        cab_service_1.CabService])
], TrainComponent);
exports.TrainComponent = TrainComponent;
//# sourceMappingURL=train.component.js.map