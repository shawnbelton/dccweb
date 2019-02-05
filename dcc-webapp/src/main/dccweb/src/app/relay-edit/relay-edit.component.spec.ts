import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {RelayEditComponent} from './relay-edit.component';

describe('RelayEditComponent', () => {
  let component: RelayEditComponent;
  let fixture: ComponentFixture<RelayEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RelayEditComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RelayEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
