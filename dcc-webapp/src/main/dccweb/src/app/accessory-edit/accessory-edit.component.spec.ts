import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {AccessoryEditComponent} from './accessory-edit.component';

describe('AccessoryEditComponent', () => {
  let component: AccessoryEditComponent;
  let fixture: ComponentFixture<AccessoryEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AccessoryEditComponent ]
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
