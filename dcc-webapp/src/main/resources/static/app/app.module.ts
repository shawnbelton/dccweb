import "./rxjs-extensions";
import {NgModule} from "@angular/core";
import {BrowserModule} from "@angular/platform-browser";
import {HttpModule} from "@angular/http";
import {AppComponent} from "./app.component";
import {TrainComponent} from "./train.component";
import {DecoderComponent} from "./decoder.component";
import {StatusComponent} from "./status.component";
import {MessageComponent} from "./message.component";
import {MessageService} from "./message.service";
import {StatusService} from "./status.service";
import {DecoderService} from "./decoder.service";
import {AppRoutingModule} from "./app.routing.module";
import {TrainService} from "./train.service";
import {FormsModule} from "@angular/forms";
import {CabComponent} from "./cab.component";
import {CabService} from "./cab.service";
import {SettingsComponent} from "./settings.component";
import {SettingsService} from "./settings.service";
import {MacroService} from "./macro.service";
import {MacroComponent} from "./macro.component";
import {PerformanceComponent} from "./performance.component";
import {PerformanceService} from "./performance.service";
import {InfoService} from "./info.service";
import {InfoComponent} from "./info.component";
import {AccessoryDecoderService} from "./accessoryDecoder.service";
import {AccessoryComponent} from "./accessory.component";
import {NotificationService} from "./notification.service";

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
        AccessoryComponent
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
        NotificationService
    ],
    bootstrap: [
        AppComponent,
        StatusComponent,
        MessageComponent
    ]
})
export class AppModule { }
