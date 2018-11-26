import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {EngineShedComponent} from './engine-shed/engine-shed.component';
import {RelayComponent} from './relay/relay.component';
import {BlockComponent} from './block/block.component';
import {DecoderComponent} from './decoder/decoder.component';
import {AccessoryComponent} from './accessory/accessory.component';
import {SettingsComponent} from './settings/settings.component';
import {NewMacroComponent} from './new-macro/new-macro.component';
import {MacroEditComponent} from './macro-edit/macro-edit.component';


const routes: Routes = [
  {path: '', redirectTo: 'engine-shed', pathMatch: 'full'},
  {path: 'engine-shed', component: EngineShedComponent, pathMatch: 'full'},
  {path: 'decoders', component: DecoderComponent, pathMatch: 'full'},
  {path: 'accessories', component: AccessoryComponent, pathMatch: 'full'},
  {path: 'settings', component: SettingsComponent, pathMatch: 'full'},
  {path: 'macro-edit', component: NewMacroComponent, pathMatch: 'full'},
  {path: 'macro-edit/:id', component: MacroEditComponent},
  {path: 'blocks', component: BlockComponent, pathMatch: 'full'},
  {path: 'relays', component: RelayComponent, pathMatch: 'full'}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
