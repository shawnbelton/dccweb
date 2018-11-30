import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {NewAccessoryComponent} from './new-accessory.component';

describe('NewAccessoryComponent', () => {
  let component: NewAccessoryComponent;
  let fixture: ComponentFixture<NewAccessoryComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NewAccessoryComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NewAccessoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
