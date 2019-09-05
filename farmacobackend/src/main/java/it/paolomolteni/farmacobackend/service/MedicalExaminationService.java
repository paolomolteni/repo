package it.paolomolteni.farmacobackend.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.paolomolteni.farmacobackend.model.MedicalExamination;
import it.paolomolteni.farmacobackend.repository.MedicalExaminationRepository;

@Component
public class MedicalExaminationService {
	
	/**
	 * 
	 */
	@Autowired
	private MedicalExaminationRepository medicalExaminationRepository;
	
	/**
	 * @param medicalExamination
	 */
	@Transactional
	public void delete(MedicalExamination medicalExamination) {
		medicalExaminationRepository.delete(medicalExamination);
	}

}
