import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {BlockItemComponent} from './block-item.component';
import {ActivatedRoute, Router} from '@angular/router';
import {RouterTestingModule} from '@angular/router/testing';
import {FormsModule} from '@angular/forms';
import {BlockService} from '../block.service';
import {of} from "rxjs";

describe('BlockItemComponent', () => {
  let component: BlockItemComponent;
  let fixture: ComponentFixture<BlockItemComponent>;

  beforeEach(async(() => {
    const router = jasmine.createSpyObj('Router', ['navigate']);
    const blockService = jasmine.createSpyObj('BlockService', ['setBlockOccupancy', 'deleteBlock']);
    TestBed.configureTestingModule({
      imports: [RouterTestingModule, FormsModule],
      declarations: [BlockItemComponent],
      providers: [
        {provide: Router, useValue: router},
        {provide: BlockService, useValue: blockService},
        {
          provide: ActivatedRoute, useValue: {
            params: of({id: 1})
          }
        }
      ]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BlockItemComponent);
    component = fixture.componentInstance;
    component.block = {
      blockId: '1',
      blockName: 'Block',
      occupied: false,
      macroId: null
    };
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
