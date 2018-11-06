import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {DecoderSettingsComponent} from './decoder-settings.component';

describe('DecoderSettingsComponent', () => {
  let component: DecoderSettingsComponent;
  let fixture: ComponentFixture<DecoderSettingsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DecoderSettingsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DecoderSettingsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
