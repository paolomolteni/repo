import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FarmacolistComponent } from './farmacolist.component';

describe('FarmacolistComponent', () => {
  let component: FarmacolistComponent;
  let fixture: ComponentFixture<FarmacolistComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FarmacolistComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FarmacolistComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
