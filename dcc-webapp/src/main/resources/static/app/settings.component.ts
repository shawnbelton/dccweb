/**
 * Created by shawn on 25/11/16.
 */
import {Component, OnInit} from "@angular/core";
import {Settings} from "./settings";
import {SettingsService} from "./settings.service";
@Component({
    moduleId: module.id,
    selector: 'dcc-settings',
    templateUrl: '/settings/settings.html'
})
export class SettingsComponent implements OnInit {

    constructor(private settingsService: SettingsService) {}

    settings: Settings;

    getSettings(): void {
        this.settingsService.getSettings().subscribe(settings => this.setSettings(settings));
    }

    saveSettings(): void {
        this.settingsService.saveSettings(this.settings);
    }

    setSettings(settings: Settings): void {
        this.settings = settings;
    }

    ngOnInit(): void {
        this.getSettings();
    }
}
