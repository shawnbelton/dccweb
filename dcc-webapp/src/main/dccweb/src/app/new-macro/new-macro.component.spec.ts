import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {NewMacroComponent} from './new-macro.component';

describe('NewMacroComponent', () => {
  let component: NewMacroComponent;
  let fixture: ComponentFixture<NewMacroComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NewMacroComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NewMacroComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
