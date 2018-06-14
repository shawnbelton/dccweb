import {BrowserModule} from "@angular/platform-browser";
import {NgModule} from "@angular/core";

import {FormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import {StompConfig, StompService} from '@stomp/ng2-stompjs';

import * as SockJS from 'sockjs-client';
import {AppRelayComponent} from "./app.relay.component";
import {AppRegisterComponent} from "./app.register.component";
import {RegisterService} from "./register.service";
import {RelayService} from "./relay.service";

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
    AppRelayComponent,
    AppRegisterComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [
    RegisterService,
    RelayService,
    StompService,
    {
      provide: StompConfig,
      useValue: stompConfig
    }
  ],
  bootstrap: [
    AppRelayComponent,
    AppRegisterComponent,
  ]
})
export class AppModule { }
