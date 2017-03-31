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
/**
 * Created by shawn on 16/11/16.
 */
var core_1 = require("@angular/core");
var train_1 = require("../models/train");
var train_service_1 = require("../services/train.service");
var decoder_service_1 = require("../services/decoder.service");
var decoder_1 = require("../models/decoder");
var cab_service_1 = require("../services/cab.service");
var TrainComponent = (function () {
    function TrainComponent(trainService, decoderService, cabService) {
        this.trainService = trainService;
        this.decoderService = decoderService;
        this.cabService = cabService;
        this.mode = false;
    }
    TrainComponent.prototype.getTrains = function () {
        var _this = this;
        this.trainService.getTrains().subscribe(function (trains) { return _this.trains = trains; });
    };
    TrainComponent.prototype.getDecoders = function () {
        var _this = this;
        this.decoderService.getDecoders().subscribe(function (decoders) { return _this.decoders = decoders; });
    };
    TrainComponent.prototype.configure = function (train) {
        train.showConfig = !train.showConfig;
        if (train.decoder == null) {
            train.decoder = new decoder_1.Decoder();
        }
    };
    TrainComponent.prototype.assignDecoder = function (train) {
        this.trainService.saveTrain(train);
        train.showConfig = false;
    };
    TrainComponent.prototype.newTrain = function () {
        this.mode = true;
        this.editTrain = new train_1.Train();
    };
    TrainComponent.prototype.startEditTrain = function (train) {
        this.mode = true;
        this.editTrain = train;
    };
    TrainComponent.prototype.cancelEdit = function () {
        this.mode = false;
        this.editTrain = null;
    };
    TrainComponent.prototype.saveTrain = function () {
        this.trainService.saveTrain(this.editTrain);
        this.mode = false;
        this.editTrain = null;
    };
    TrainComponent.prototype.driveTrain = function (train) {
        this.cabService.setTrain(train);
    };
    TrainComponent.prototype.ngOnInit = function () {
        this.getTrains();
        this.getDecoders();
    };
    TrainComponent = __decorate([
        core_1.Component({
            moduleId: module.id,
            templateUrl: '../../train/train.html'
        }), 
        __metadata('design:paramtypes', [train_service_1.TrainService, decoder_service_1.DecoderService, cab_service_1.CabService])
    ], TrainComponent);
    return TrainComponent;
}());
exports.TrainComponent = TrainComponent;
//# sourceMappingURL=train.component.js.map