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
    { path: 'engine-shed', component: TrainComponent, pathMatch: 'full' },
    { path: 'decoders', component: DecoderComponent, pathMatch: 'full' },
    { path: 'accessories', component: AccessoryComponent, pathMatch: 'full' },
    { path: 'settings', component: SettingsComponent, pathMatch: 'full' },
    { path: 'macro-edit', component: MacroComponent, pathMatch: 'full' },
    { path: 'performance', component: PerformanceComponent, pathMatch: 'full' },
    { path: 'blocks', component: BlockComponent, pathMatch: 'full' },
    { path: 'relays', component: RelayComponent, pathMatch: 'full' }
];

@NgModule({
    imports: [ RouterModule.forRoot(routes) ],
    exports: [ RouterModule ]
})
export class AppRoutingModule {}
