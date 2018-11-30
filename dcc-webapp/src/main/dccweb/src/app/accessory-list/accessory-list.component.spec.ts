import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {AccessoryListComponent} from './accessory-list.component';

describe('AccessoryListComponent', () => {
  let component: AccessoryListComponent;
  let fixture: ComponentFixture<AccessoryListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AccessoryListComponent ]
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
