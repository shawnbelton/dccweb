import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {MacroListComponent} from './macro-list.component';

describe('MacroListComponent', () => {
  let component: MacroListComponent;
  let fixture: ComponentFixture<MacroListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MacroListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MacroListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
