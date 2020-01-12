import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {RelayListComponent} from './relay-list.component';

describe('RelayListComponent', () => {
  let component: RelayListComponent;
  let fixture: ComponentFixture<RelayListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RelayListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RelayListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
