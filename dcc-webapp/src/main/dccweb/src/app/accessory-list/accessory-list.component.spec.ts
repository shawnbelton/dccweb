import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {AccessoryListComponent} from './accessory-list.component';
import {CUSTOM_ELEMENTS_SCHEMA} from '@angular/core';
import {RouterTestingModule} from '@angular/router/testing';
import {FormsModule} from '@angular/forms';
import {AccessoryDecoderService} from '../accessory-decoder.service';
import {of} from 'rxjs';

describe('AccessoryListComponent', () => {
  let component: AccessoryListComponent;
  let fixture: ComponentFixture<AccessoryListComponent>;

  beforeEach(async(() => {
    const accessoryService = jasmine.createSpyObj('AccessoryDecoderService', {'getAccessories': of([{accessoryDecoderId: 1}])});
    TestBed.configureTestingModule({
      imports: [RouterTestingModule, FormsModule],
      declarations: [AccessoryListComponent],
      providers: [
        {provide: AccessoryDecoderService, useValue: accessoryService}
      ],
      schemas: [CUSTOM_ELEMENTS_SCHEMA]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AccessoryListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
