import "./rxjs-extensions";
import {NgModule} from "@angular/core";
import {BrowserModule} from "@angular/platform-browser";
import {HttpModule} from "@angular/http";
import {AppComponent} from "./components/app.component";
import {TrainComponent} from "./components/train.component";
import {DecoderComponent} from "./components/decoder.component";
import {StatusComponent} from "./components/status.component";
import {MessageComponent} from "./components/message.component";
import {MessageService} from "./services/message.service";
import {StatusService} from "./services/status.service";
import {DecoderService} from "./services/decoder.service";
import {AppRoutingModule} from "./app.routing.module";
import {TrainService} from "./services/train.service";
import {FormsModule} from "@angular/forms";
import {CabComponent} from "./components/cab.component";
import {CabService} from "./services/cab.service";
import {SettingsComponent} from "./components/settings.component";
import {SettingsService} from "./services/settings.service";
import {MacroService} from "./services/macro.service";
import {MacroComponent} from "./components/macro.component";
import {PerformanceComponent} from "./components/performance.component";
import {PerformanceService} from "./services/performance.service";
import {InfoService} from "./services/info.service";
import {InfoComponent} from "./components/info.component";
import {AccessoryDecoderService} from "./services/accessoryDecoder.service";
import {AccessoryComponent} from "./components/accessory.component";
import {NotificationService} from "./services/notification.service";
import {BlockComponent} from "./components/block.component";
import {BlockService} from "./services/block.service";
import {RelayComponent} from "./components/relay.component";
import {RelayService} from "./services/relay.service";

@NgModule({
    imports: [
        BrowserModule,
        HttpModule,
        FormsModule,
        AppRoutingModule
    ],
    declarations: [
        AppComponent,
        StatusComponent,
        MessageComponent,
        TrainComponent,
        DecoderComponent,
        CabComponent,
        SettingsComponent,
        MacroComponent,
        PerformanceComponent,
        InfoComponent,
        AccessoryComponent,
        BlockComponent,
        RelayComponent
    ],
    providers: [
        MessageService,
        StatusService,
        DecoderService,
        TrainService,
        CabService,
        SettingsService,
        MacroService,
        PerformanceService,
        InfoService,
        AccessoryDecoderService,
        NotificationService,
        BlockService,
        RelayService
    ],
    bootstrap: [
        AppComponent,
        StatusComponent,
        MessageComponent
    ]
})
export class AppModule { }
