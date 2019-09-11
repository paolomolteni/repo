package it.paolomolteni.farmacobackend.service;

import java.util.List;

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
	
	@Transactional
	public void save(List<MedicalExamination> examinations) {
		
		for(MedicalExamination examination : examinations) {
			medicalExaminationRepository.save(examination);
		}
		
	}

}
