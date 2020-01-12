import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DecoderEditorComponent } from './decoder-editor.component';

describe('DecoderEditorComponent', () => {
  let component: DecoderEditorComponent;
  let fixture: ComponentFixture<DecoderEditorComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DecoderEditorComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DecoderEditorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
