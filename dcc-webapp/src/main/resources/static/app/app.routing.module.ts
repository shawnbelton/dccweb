/**
 * Created by shawn on 16/11/16.
 */
import {NgModule} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";
import {TrainComponent} from "./train.component";
import {DecoderComponent} from "./decoder.component";
import {SettingsComponent} from "./settings.component";

const routes: Routes = [
    { path: '', redirectTo: '/engineshed', pathMatch: 'full'},
    { path: 'engineshed', component: TrainComponent },
    { path: 'decoders', component: DecoderComponent },
    { path: 'settings', component: SettingsComponent }
];

@NgModule({
    imports: [ RouterModule.forRoot(routes) ],
    exports: [ RouterModule ]
})
export class AppRoutingModule {}
