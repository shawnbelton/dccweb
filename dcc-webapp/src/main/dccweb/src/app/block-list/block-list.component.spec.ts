import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {BlockListComponent} from './block-list.component';
import {of} from 'rxjs';
import {BlockService} from '../block.service';
import {CUSTOM_ELEMENTS_SCHEMA} from '@angular/core';

describe('BlockListComponent', () => {
  let component: BlockListComponent;
  let fixture: ComponentFixture<BlockListComponent>;

  beforeEach(async(() => {
    const blockService = jasmine.createSpyObj('BlockService', {
      'getBlocks': of([{
        blockId: 1,
        blockName: 'Block1',
        occupied: true,
        macroId: null
      }])
    });
    TestBed.configureTestingModule({
      declarations: [BlockListComponent],
      providers: [
        {provide: BlockService, useValue: blockService}
      ],
      schemas: [CUSTOM_ELEMENTS_SCHEMA]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BlockListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
