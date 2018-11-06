import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {AccessoryComponent} from './accessory.component';

describe('AccessoryComponent', () => {
  let component: AccessoryComponent;
  let fixture: ComponentFixture<AccessoryComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AccessoryComponent ]
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
