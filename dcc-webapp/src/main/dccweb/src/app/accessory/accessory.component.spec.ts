import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {AccessoryComponent} from './accessory.component';
import {AccessoryDecoderService} from '../accessory-decoder.service';
import {MacroService} from '../macro.service';
import {RouterTestingModule} from '@angular/router/testing';
import {FormsModule} from '@angular/forms';
import {of} from 'rxjs';

describe('AccessoryComponent', () => {
  let component: AccessoryComponent;
  let fixture: ComponentFixture<AccessoryComponent>;

  beforeEach(async(() => {
    const accessoryDecoderService = jasmine.createSpyObj('AccessoryDecoderService', {
      'getAccessoryTypes': of([
        {
          decoderTypeId: 1,
          decoderType: 'Point'
        }
      ]), 'saveAccessory': null
    });
    const macroService = jasmine.createSpyObj('MacroService', {
      'getMacros': of([
        {
          macroId: 1,
          name: 'Macro'
        }
      ])
    });
    TestBed.configureTestingModule({
      imports: [RouterTestingModule, FormsModule],
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
    component.editAccessory = {
      address: 123,
      accessoryDecoderId: 1,
      accessoryDecoderType: {
        decoderTypeId: 1,
        decoderType: 'Point',
        decoderTypeCode: 'POINT',
        decoderTypeOperations: null
      },
      name: 'Test Point',
      currentValue: 1,
      macroId: null
    };
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
