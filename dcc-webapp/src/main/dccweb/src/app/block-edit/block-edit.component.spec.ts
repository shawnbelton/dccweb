import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {BlockEditComponent} from './block-edit.component';
import {CUSTOM_ELEMENTS_SCHEMA} from '@angular/core';
import {RouterTestingModule} from '@angular/router/testing';
import {FormsModule} from '@angular/forms';
import {BlockService} from '../block.service';
import {ActivatedRoute, Router} from '@angular/router';
import {of} from 'rxjs';
import {MacroService} from '../macro.service';

describe('BlockEditComponent', () => {
  let component: BlockEditComponent;
  let fixture: ComponentFixture<BlockEditComponent>;

  beforeEach(async(() => {
    const blockService = jasmine.createSpyObj('BlockService', {
      'saveBlock': null,
      'getBlock': {
        blockId: 1,
        blockName: 'Block1',
        occupied: true,
        macroId: null
      }
    });
    const router = jasmine.createSpyObj('Router', ['navigate']);
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
      declarations: [BlockEditComponent],
      providers: [
        {provide: BlockService, useValue: blockService},
        {provide: MacroService, useValue: macroService},
        {
          provide: ActivatedRoute, useValue: {
            params: of({id: 1})
          }
        },
        {provide: Router, useValue: router}
      ],
      schemas: [CUSTOM_ELEMENTS_SCHEMA]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BlockEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
