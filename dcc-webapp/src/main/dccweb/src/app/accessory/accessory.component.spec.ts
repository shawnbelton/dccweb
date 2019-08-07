import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {AccessoryComponent} from './accessory.component';
import {AccessoryDecoderService} from '../accessory-decoder.service';
import {MacroService} from '../macro.service';

describe('AccessoryComponent', () => {
  let component: AccessoryComponent;
  let fixture: ComponentFixture<AccessoryComponent>;

  beforeEach(async(() => {
    const accessoryDecoderService = jasmine.createSpyObj('AccessoryDecoderService', ['getAccessoryTypes', 'saveAccessory']);
    const macroService = jasmine.createSpyObj('MacroService', ['getMacros'])
    TestBed.configureTestingModule({
      declarations: [AccessoryComponent],
      providers: [
        {provide: AccessoryDecoderService, useValue: accessoryDecoderService},
        {provide: MacroService, useValue: macroService}
      ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AccessoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
