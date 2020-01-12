import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {DecoderSettingEditorComponent} from './decoder-setting-editor.component';

describe('DecoderSettingEditorComponent', () => {
  let component: DecoderSettingEditorComponent;
  let fixture: ComponentFixture<DecoderSettingEditorComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DecoderSettingEditorComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DecoderSettingEditorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
