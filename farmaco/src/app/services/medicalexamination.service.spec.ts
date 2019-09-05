import { TestBed } from '@angular/core/testing';

import { MedicalExaminationService } from './medicalexamination.service';

describe('MedicalexaminationService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: MedicalExaminationService = TestBed.get(MedicalExaminationService);
    expect(service).toBeTruthy();
  });
});
