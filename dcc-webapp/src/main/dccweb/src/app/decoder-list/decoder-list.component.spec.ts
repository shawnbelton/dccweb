import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DecoderListComponent } from './decoder-list.component';

describe('DecoderListComponent', () => {
  let component: DecoderListComponent;
  let fixture: ComponentFixture<DecoderListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DecoderListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DecoderListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
