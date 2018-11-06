import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {EngineShedComponent} from './engine-shed.component';

describe('EngineShedComponent', () => {
  let component: EngineShedComponent;
  let fixture: ComponentFixture<EngineShedComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EngineShedComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EngineShedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
