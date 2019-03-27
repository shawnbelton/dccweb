import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {HeaderComponent} from './header/header.component';
import {FooterComponent} from './footer/footer.component';
import {StatusComponent} from './status/status.component';
import {MessagesComponent} from './messages/messages.component';
import {EngineShedComponent} from './engine-shed/engine-shed.component';
import {CabComponent} from './cab/cab.component';
import {PaginationComponent} from './pagination/pagination.component';
import {FormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';

import * as SockJS from 'sockjs-client';
import {StompConfig, StompService} from '@stomp/ng2-stompjs';
import {AccessoryComponent} from './accessory/accessory.component';
import {BlockComponent} from './block/block.component';
import {InfoComponent} from './info/info.component';
import {MacroEditComponent} from './macro-edit/macro-edit.component';
import {RelayComponent} from './relay/relay.component';
import {SettingsComponent} from './settings/settings.component';
import {DecoderComponent} from './decoder/decoder.component';
import {DecoderSettingsComponent} from './decoder-settings/decoder-settings.component';
import {BlockItemComponent} from './block-item/block-item.component';
import {MacroListComponent} from './macro-list/macro-list.component';
import {NewMacroComponent} from './new-macro/new-macro.component';
import {EditMacroItemComponent} from './edit-macro-item/edit-macro-item.component';
import {RelayListComponent} from './relay-list/relay-list.component';
import {RelayEditComponent} from './relay-edit/relay-edit.component';
import {BlockListComponent} from './block-list/block-list.component';
import {BlockEditComponent} from './block-edit/block-edit.component';
import {AccessoryListComponent} from './accessory-list/accessory-list.component';
import {AccessoryEditComponent} from './accessory-edit/accessory-edit.component';
import {NewAccessoryComponent} from './new-accessory/new-accessory.component';
import {DecoderListComponent} from './decoder-list/decoder-list.component';
import {DecoderEditComponent} from './decoder-edit/decoder-edit.component';
import {DecoderEditorComponent} from './decoder-editor/decoder-editor.component';
import {DecoderReadComponent} from './decoder-read/decoder-read.component';
import {DecoderSettingEditorComponent} from './decoder-setting-editor/decoder-setting-editor.component';

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
    HeaderComponent,
    FooterComponent,
    StatusComponent,
    MessagesComponent,
    EngineShedComponent,
    CabComponent,
    PaginationComponent,
    AccessoryComponent,
    BlockComponent,
    InfoComponent,
    MacroEditComponent,
    RelayComponent,
    SettingsComponent,
    DecoderComponent,
    DecoderSettingsComponent,
    BlockItemComponent,
    MacroListComponent,
    NewMacroComponent,
    EditMacroItemComponent,
    RelayListComponent,
    RelayEditComponent,
    BlockListComponent,
    BlockEditComponent,
    AccessoryListComponent,
    AccessoryEditComponent,
    NewAccessoryComponent,
    DecoderListComponent,
    DecoderEditComponent,
    DecoderEditorComponent,
    DecoderReadComponent,
    DecoderSettingEditorComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    AppRoutingModule
  ],
  providers: [
    StompService,
    {
      provide: StompConfig,
      useValue: stompConfig
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
