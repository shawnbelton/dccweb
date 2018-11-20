import {Component, OnInit} from '@angular/core';
import {Settings} from '../models/settings';
import {InterfaceInfo} from '../models/interface-info';
import {SettingsService} from '../settings.service';

@Component({
  selector: 'app-settings',
  templateUrl: './settings.component.html',
  styleUrls: ['./settings.component.css']
})
export class SettingsComponent implements OnInit {

  settings: Settings;
  interfaces: InterfaceInfo[];

  constructor(private settingsService: SettingsService) {
  }

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
