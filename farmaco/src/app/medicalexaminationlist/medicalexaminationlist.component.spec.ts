import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MedicalexaminationlistComponent } from './medicalexaminationlist.component';

describe('MedicalexaminationlistComponent', () => {
  let component: MedicalexaminationlistComponent;
  let fixture: ComponentFixture<MedicalexaminationlistComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MedicalexaminationlistComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MedicalexaminationlistComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
