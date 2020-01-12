import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {AccessoryEditComponent} from './accessory-edit.component';
import {AccessoryDecoderService} from '../accessory-decoder.service';
import {ActivatedRoute, Router} from '@angular/router';
import {RouterTestingModule} from '@angular/router/testing';
import {FormsModule} from '@angular/forms';
import {CUSTOM_ELEMENTS_SCHEMA} from '@angular/core';
import {of} from 'rxjs';

describe('AccessoryEditComponent', () => {
  let component: AccessoryEditComponent;
  let fixture: ComponentFixture<AccessoryEditComponent>;

  beforeEach(async(() => {
    const accessoryService = jasmine.createSpyObj('AccessoryDecoderService', ['getAccessory']);
    const router = jasmine.createSpyObj('Router', ['navigate']);
    TestBed.configureTestingModule({
      imports: [RouterTestingModule, FormsModule],
      declarations: [AccessoryEditComponent],
      providers: [
        {provide: AccessoryDecoderService, useValue: accessoryService},
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
    fixture = TestBed.createComponent(AccessoryEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
