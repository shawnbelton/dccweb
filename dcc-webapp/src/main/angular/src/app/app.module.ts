import {BrowserModule} from "@angular/platform-browser";
import {NgModule} from "@angular/core";

import {AppComponent} from "./app.component";
import {FormsModule} from "@angular/forms";
import {AppRoutingModule} from "./app.routing.module";
import {RelayComponent} from "./components/relay.component";
import {BlockComponent} from "./components/block.component";
import {AccessoryComponent} from "./components/accessory.component";
import {StatusComponent} from "./components/status.component";
import {MessageComponent} from "./components/message.component";
import {InfoComponent} from "./components/info.component";
import {PerformanceComponent} from "./components/performance.component";
import {MacroComponent} from "./components/macro.component";
import {SettingsComponent} from "./components/settings.component";
import {CabComponent} from "./components/cab.component";
import {DecoderComponent} from "./components/decoder.component";
import {LocoComponent} from "./components/loco.component";
import {RelayService} from "./services/relay.service";
import {BlockService} from "./services/block.service";
import {MacroService} from "./services/macro.service";
import {SettingsService} from "./services/settings.service";
import {CabService} from "./services/cab.service";
import {LocoService} from "./services/loco.service";
import {DecoderService} from "./services/decoder.service";
import {StatusService} from "./services/status.service";
import {MessageService} from "./services/message.service";
import {PerformanceService} from "./services/performance.service";
import {InfoService} from "./services/info.service";
import {AccessoryDecoderService} from "./services/accessoryDecoder.service";
import {BlockItem} from "./components/block.item.component";
import {PaginationComponent} from "./components/pagination.component";
import {DecoderSettingComponent} from "./components/decoderSettings/decoderSetting.component";
import {HttpClientModule} from "@angular/common/http";
import {StompConfig, StompService} from '@stomp/ng2-stompjs';

import * as SockJS from 'sockjs-client';

export function socketProvider() {
  return new SockJS('/socket');
}

const stompConfig: StompConfig = {

  url: socketProvider,

  // Headers
  // Typical keys: login, passcode, host
  headers: {
  },

  // How often to heartbeat?
  // Interval in milliseconds, set to 0 to disable
  heartbeat_in: 0, // Typical value 0 - disabled
  heartbeat_out: 20000, // Typical value 20000 - every 20 seconds
  // Wait in milliseconds before attempting auto reconnect
  // Set to 0 to disable
  // Typical value 5000 (5 seconds)
  reconnect_delay: 5000,

  // Will log diagnostics on console
  debug: true
};

@NgModule({
  declarations: [
    AppComponent,
    StatusComponent,
    MessageComponent,
    PaginationComponent,
    LocoComponent,
    DecoderComponent,
    CabComponent,
    SettingsComponent,
    MacroComponent,
    PerformanceComponent,
    InfoComponent,
    AccessoryComponent,
    BlockComponent,
    RelayComponent,
    BlockItem,
    DecoderSettingComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    AppRoutingModule
  ],
  providers: [
    MessageService,
    StatusService,
    DecoderService,
    LocoService,
    CabService,
    SettingsService,
    MacroService,
    PerformanceService,
    InfoService,
    AccessoryDecoderService,
    BlockService,
    RelayService,
    StompService,
    {
      provide: StompConfig,
      useValue: stompConfig
    }
  ],
  bootstrap: [
    AppComponent,
    StatusComponent,
    MessageComponent
  ]
})
export class AppModule { }
