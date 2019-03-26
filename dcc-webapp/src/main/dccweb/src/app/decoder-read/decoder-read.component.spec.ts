import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {DecoderReadComponent} from './decoder-read.component';

describe('DecoderReadComponent', () => {
  let component: DecoderReadComponent;
  let fixture: ComponentFixture<DecoderReadComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DecoderReadComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DecoderReadComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
