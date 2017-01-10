/**
 * Created by shawn on 16/11/16.
 */
import {NgModule} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";
import {TrainComponent} from "./train.component";
import {DecoderComponent} from "./decoder.component";
import {SettingsComponent} from "./settings.component";
import {MacroComponent} from "./macro.component";
import {PerformanceComponent} from "./performance.component";

const routes: Routes = [
    { path: '', redirectTo: '/engine-shed', pathMatch: 'full'},
    { path: 'engine-shed', component: TrainComponent },
    { path: 'decoders', component: DecoderComponent },
    { path: 'settings', component: SettingsComponent },
    { path: 'macro-edit', component: MacroComponent },
    { path: 'performance', component: PerformanceComponent }
];

@NgModule({
    imports: [ RouterModule.forRoot(routes) ],
    exports: [ RouterModule ]
})
export class AppRoutingModule {}
