package it.paolomolteni.farmacobackend.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.paolomolteni.farmacobackend.model.Medicine;
import it.paolomolteni.farmacobackend.repository.MedicineRepository;

@Component
public class MedicineService {
	
	/**
	 * 
	 */
	@Autowired
	private MedicineRepository medicineRepository;
	
	/**
	 * @param medicine
	 */
	@Transactional
	public void delete(Medicine medicine) {
		medicineRepository.delete(medicine);
	}

}
