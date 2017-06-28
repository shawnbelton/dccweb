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
 * Created by shawn on 03/04/17.
 */
const core_1 = require("@angular/core");
const block_service_1 = require("../services/block.service");
const block_1 = require("../models/block");
const macro_1 = require("../models/macro");
const macro_service_1 = require("../services/macro.service");
let BlockComponent = class BlockComponent {
    constructor(blockService, macroService) {
        this.blockService = blockService;
        this.macroService = macroService;
    }
    getBlocks() {
        this.blockService.getBlocks().subscribe(data => this.blocks = data);
    }
    getMacros() {
        this.macroService.getMacros().subscribe(macros => this.setMacros(macros));
    }
    setMacros(macros) {
        this.macros = macros;
    }
    saveBlock() {
        if (this.block.macro.macroId == null || this.block.macro.macroId.toString() == "") {
            this.block.macro = null;
        }
        this.blockService.saveBlock(this.block);
        this.resetBlock();
    }
    deleteBlock(block) {
        this.blockService.deleteBlock(block);
        this.resetBlock();
    }
    resetBlock() {
        let block = new block_1.Block();
        block.macro = new macro_1.Macro();
        this.setBlock(block);
    }
    setBlock(block) {
        this.block = block;
    }
    startBlockEdit(block) {
        if (block.macro == null) {
            block.macro = new macro_1.Macro();
        }
        this.setBlock(block);
    }
    cancelEdit() {
        this.resetBlock();
    }
    setBlockOccupancy(block, state) {
        block.occupied = state;
        this.blockService.setBlockOccupancy(block);
    }
    ngOnInit() {
        this.resetBlock();
        this.getMacros();
        this.getBlocks();
    }
};
BlockComponent = __decorate([
    core_1.Component({
        moduleId: module.id,
        templateUrl: '../../block/block.html',
    }),
    __metadata("design:paramtypes", [block_service_1.BlockService, macro_service_1.MacroService])
], BlockComponent);
exports.BlockComponent = BlockComponent;
//# sourceMappingURL=block.component.js.map