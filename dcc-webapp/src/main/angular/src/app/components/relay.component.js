"use strict";
/**
 * Created by shawn on 07/06/17.
 */
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
const core_1 = require("@angular/core");
const relayController_1 = require("../models/relayController");
const relay_service_1 = require("../services/relay.service");
let RelayComponent = class RelayComponent {
    constructor(relayService) {
        this.relayService = relayService;
    }
    getRelayControllers() {
        this.relayService.getRelayControllers().subscribe(data => this.relayControllers = data);
    }
    saveRelayController() {
        this.relayService.saveRelayController(this.relayController);
        this.resetRelayController();
    }
    deleteRelayController() {
        this.resetRelayController();
    }
    resetRelayController() {
        let relayController = new relayController_1.RelayController();
        this.setRelayController(relayController);
    }
    setRelayController(relayController) {
        this.relayController = relayController;
    }
    startRelayControllerEdit(relayController) {
        this.setRelayController(relayController);
    }
    cancelEdit() {
        this.resetRelayController();
    }
    showName(relay, index) {
        return "" + (index + 1);
    }
    switchStatus(relay, index) {
        return (relay.value & Math.pow(2, index)) > 0;
    }
    switchChange(relay, index) {
        relay.value ^= Math.pow(2, index);
        this.relayService.updateRelayValue(relay);
    }
    ngOnInit() {
        this.resetRelayController();
        this.getRelayControllers();
    }
};
RelayComponent = __decorate([
    core_1.Component({
        moduleId: module.id,
        templateUrl: '../../relay/relay.html',
    }),
    __metadata("design:paramtypes", [relay_service_1.RelayService])
], RelayComponent);
exports.RelayComponent = RelayComponent;
//# sourceMappingURL=relay.component.js.map