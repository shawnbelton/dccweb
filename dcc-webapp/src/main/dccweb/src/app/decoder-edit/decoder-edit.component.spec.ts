import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DecoderEditComponent } from './decoder-edit.component';

describe('DecoderEditComponent', () => {
  let component: DecoderEditComponent;
  let fixture: ComponentFixture<DecoderEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DecoderEditComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DecoderEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
