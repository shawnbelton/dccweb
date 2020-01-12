import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {EditMacroItemComponent} from './edit-macro-item.component';

describe('EditMacroItemComponent', () => {
  let component: EditMacroItemComponent;
  let fixture: ComponentFixture<EditMacroItemComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditMacroItemComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditMacroItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
