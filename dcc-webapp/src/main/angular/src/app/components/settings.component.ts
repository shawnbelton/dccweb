/**
 * Created by shawn on 25/11/16.
 */
import {Component, OnInit} from "@angular/core";
import {Settings} from "../models/settings";
import {SettingsService} from "../services/settings.service";
import {InterfaceInfo} from "../models/interfaceInfo";
@Component({
    moduleId: module.id,
    selector: 'dcc-settings',
    templateUrl: './../html/settings/settings.html'
})
export class SettingsComponent implements OnInit {

    constructor(private settingsService: SettingsService) {}

    settings: Settings;
    interfaces: InterfaceInfo[];

    getSettings(): void {
        this.settingsService.getSettings().subscribe(settings => this.setSettings(settings));
    }

    getInterfaces(): void {
        this.settingsService.getInterfaces().subscribe(interfaces => this.setInterfaces(interfaces));
    }

    saveSettings(): void {
        this.settingsService.saveSettings(this.settings);
    }

    setSettings(settings: Settings): void {
        this.settings = settings;
    }

    setInterfaces(interfaces: InterfaceInfo[]): void {
        this.interfaces = interfaces;
    }

    ngOnInit(): void {
        this.getSettings();
        this.getInterfaces();
    }
}
