/**
 * Created by shawn on 16/11/16.
 */
import {NgModule} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";
import {TrainComponent} from "./components/train.component";
import {DecoderComponent} from "./components/decoder.component";
import {SettingsComponent} from "./components/settings.component";
import {MacroComponent} from "./components/macro.component";
import {PerformanceComponent} from "./components/performance.component";
import {AccessoryComponent} from "./components/accessory.component";
import {BlockComponent} from "./components/block.component";
import {RelayComponent} from "./components/relay.component";

const routes: Routes = [
    { path: '', redirectTo: 'engine-shed', pathMatch: 'full'},
    { path: 'engine-shed', component: TrainComponent },
    { path: 'decoders', component: DecoderComponent },
    { path: 'accessories', component: AccessoryComponent },
    { path: 'settings', component: SettingsComponent },
    { path: 'macro-edit', component: MacroComponent },
    { path: 'performance', component: PerformanceComponent },
    { path: 'blocks', component: BlockComponent },
    { path: 'relays', component: RelayComponent }
];

@NgModule({
    imports: [ RouterModule.forRoot(routes) ],
    exports: [ RouterModule ]
})
export class AppRoutingModule {}
