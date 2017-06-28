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
 * Created by shawn on 12/01/17.
 */
const core_1 = require("@angular/core");
const info_service_1 = require("../services/info.service");
const moment = require("moment");
let InfoComponent = class InfoComponent {
    constructor(infoService) {
        this.infoService = infoService;
    }
    getInfo() {
        this.infoService.getInfo().subscribe(info => this.setInfo(info));
    }
    setInfo(info) {
        this.info = info;
    }
    toDateString(time) {
        let date = new Date(time);
        return moment(date).format('h:mm:ssa Do MMMM YYYY');
    }
    ngOnInit() {
        this.getInfo();
    }
};
InfoComponent = __decorate([
    core_1.Component({
        moduleId: module.id,
        templateUrl: '../../info/info.html',
        selector: 'dcc-info'
    }),
    __metadata("design:paramtypes", [info_service_1.InfoService])
], InfoComponent);
exports.InfoComponent = InfoComponent;
//# sourceMappingURL=info.component.js.map