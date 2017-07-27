/**
 * Created by shawn on 16/11/16.
 */
import {Component, OnInit} from "@angular/core";
import {Loco} from "../models/loco";
import {LocoService} from "../services/loco.service";
import {DecoderService} from "../services/decoder.service";
import {Decoder} from "../models/decoder";
import {CabService} from "../services/cab.service";

@Component({
    moduleId: module.id,
    templateUrl: '../html/loco/loco.html'
})
export class LocoComponent implements OnInit {

    locos: Loco[];
    editLoco: Loco;
    decoders: Decoder[];
    mode: boolean;

    constructor(private locoService: LocoService,
        private decoderService: DecoderService,
        private cabService: CabService) {
        this.mode = false;
    }

    getLocos(): void {
        this.locoService.getLocos().subscribe(locos => this.locos = locos);
    }

    getDecoders(): void {
        this.decoderService.getDecoders().subscribe(decoders => this.decoders = decoders);
    }

    configure(loco: Loco): void {
        loco.showConfig = !loco.showConfig;
        if (loco.decoder == null) {
            loco.decoder = new Decoder();
        }
    }

    assignDecoder(loco: Loco): void {
        this.locoService.saveLoco(loco);
        loco.showConfig = false;
    }

    newLoco(): void {
        this.mode = true;
        this.editLoco = new Loco();
    }

    startEditLoco(loco: Loco): void {
        this.mode = true;
        this.editLoco = loco;
    }

    cancelEdit(): void {
        this.mode = false;
        this.editLoco = null;
    }

    saveLoco(): void {
        this.locoService.saveLoco(this.editLoco);
        this.mode = false;
        this.editLoco = null;
    }

    driveLoco(loco: Loco): void {
        this.cabService.setLoco(loco);
    }

    ngOnInit(): void {
        this.getLocos();
        this.getDecoders();
    }
}
