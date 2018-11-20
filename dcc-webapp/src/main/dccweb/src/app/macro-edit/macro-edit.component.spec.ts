import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {MacroEditComponent} from './macro-edit.component';

describe('MacroEditComponent', () => {
  let component: MacroEditComponent;
  let fixture: ComponentFixture<MacroEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MacroEditComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MacroEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
