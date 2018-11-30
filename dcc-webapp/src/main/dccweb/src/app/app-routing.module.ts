import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {EngineShedComponent} from './engine-shed/engine-shed.component';
import {RelayComponent} from './relay/relay.component';
import {BlockComponent} from './block/block.component';
import {DecoderComponent} from './decoder/decoder.component';
import {SettingsComponent} from './settings/settings.component';
import {NewMacroComponent} from './new-macro/new-macro.component';
import {MacroEditComponent} from './macro-edit/macro-edit.component';
import {RelayEditComponent} from './relay-edit/relay-edit.component';
import {BlockEditComponent} from './block-edit/block-edit.component';
import {AccessoryEditComponent} from './accessory-edit/accessory-edit.component';
import {NewAccessoryComponent} from './new-accessory/new-accessory.component';

const routes: Routes = [
  {path: '', redirectTo: 'engine-shed', pathMatch: 'full'},
  {path: 'engine-shed', component: EngineShedComponent, pathMatch: 'full'},
  {path: 'decoders', component: DecoderComponent, pathMatch: 'full'},
  {path: 'accessories', component: NewAccessoryComponent, pathMatch: 'full'},
  {path: 'accessories/:id', component: AccessoryEditComponent},
  {path: 'settings', component: SettingsComponent, pathMatch: 'full'},
  {path: 'macro-edit', component: NewMacroComponent, pathMatch: 'full'},
  {path: 'macro-edit/:id', component: MacroEditComponent},
  {path: 'blocks', component: BlockComponent, pathMatch: 'full'},
  {path: 'blocks/:id', component: BlockEditComponent},
  {path: 'relays', component: RelayComponent, pathMatch: 'full'},
  {path: 'relays/:id', component: RelayEditComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
