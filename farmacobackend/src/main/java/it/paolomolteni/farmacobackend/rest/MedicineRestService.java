package it.paolomolteni.farmacobackend.rest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.paolomolteni.farmacobackend.json.Response;
import it.paolomolteni.farmacobackend.model.Medicine;
import it.paolomolteni.farmacobackend.model.Person;
import it.paolomolteni.farmacobackend.repository.MedicineRepository;
import it.paolomolteni.farmacobackend.repository.PersonRepository;
import it.paolomolteni.farmacobackend.service.MedicineService;

@CrossOrigin
@RestController
@RequestMapping("/medicine")
public class MedicineRestService {
	
	/**
	 * 
	 */
	@Autowired
	private MedicineRepository medicineRepository;
	
	/**
	 * 
	 */
	@Autowired
	private PersonRepository personRepository;
	
	/**
	 * 
	 */
	@Autowired
	private MedicineService medicineService;
	
	/**
	 * @return
	 * http://localhost:8888/medicine/list
	 */
	@GetMapping(path = "/list")
    public List<it.paolomolteni.farmacobackend.json.Medicine> getList() {
		List<it.paolomolteni.farmacobackend.json.Medicine> list = new ArrayList<it.paolomolteni.farmacobackend.json.Medicine>();
		List<Medicine> medicines = medicineRepository.getMedicines();

		for(Medicine medicine : medicines) {
			list.add(mapMedicine(medicine));
		}
		
		return list;
    }
	
	/**
	 * @param id
	 * @return
	 */
	@GetMapping(path = "/list/person")
    public List<it.paolomolteni.farmacobackend.json.Medicine> getListByPerson(@RequestParam(value="personId") Long personId) {
		List<it.paolomolteni.farmacobackend.json.Medicine> list = new ArrayList<it.paolomolteni.farmacobackend.json.Medicine>();
		
		List<Medicine> medicines = new ArrayList<Medicine>();
		
		if(personId != null) {
			Person person = personRepository.findById(personId).orElse(null);
			
			if(person != null) {
				medicines.addAll(medicineRepository.getMedicinesByPerson(person));
			}
		}

		for(Medicine medicine : medicines) {
			list.add(mapMedicine(medicine));
		}
		
		return list;
    }
	
	/**
	 * @param farmaco
	 * http://localhost:8888/medicine/save
	 */
	@PostMapping(path = "/save")
	public it.paolomolteni.farmacobackend.json.Medicine save(@RequestBody it.paolomolteni.farmacobackend.json.Medicine medicine) {
		Medicine model = null;
		
		if(medicine.id != null) {
			model =	medicineRepository.findById(medicine.id).orElse(null);
		}
		
		if(model == null) {
			model = new Medicine();
		}

		model.setDate(new Date(medicine.tsDate));
		
		if(medicine.tsDateExpiry != null) {
			model.setDateExpiry(new Date(medicine.tsDateExpiry));
		}
		else {
			model.setDateExpiry(null);
		}
		
		if(medicine.tsDateExpiryWhenOpened != null) {
			model.setDateExpiryWhenOpened(new Date(medicine.tsDateExpiryWhenOpened));
		}
		else {
			model.setDateExpiryWhenOpened(null);
		}
		
		model.setName(medicine.name);
		model.setDescription(medicine.description);
		model.setCause(medicine.cause);
		
		Person person = this.personRepository.findById(medicine.personId).orElse(null);
		model.setPerson(person);
		
		model = medicineRepository.save(model);
		
		return mapMedicine(model);
	}
	
	/**
	 * @param farmaco
	 * @return
	 */
	private it.paolomolteni.farmacobackend.json.Medicine mapMedicine(Medicine medicine){
		it.paolomolteni.farmacobackend.json.Medicine json = new it.paolomolteni.farmacobackend.json.Medicine(medicine.getId(), medicine.getDate().getTime(), medicine.getName(), medicine.getDescription(),
				medicine.getDateExpiry() != null ? medicine.getDateExpiry().getTime() : null, 
				medicine.getDateExpiryWhenOpened() != null ? medicine.getDateExpiryWhenOpened().getTime() : null, 
				medicine.getCause());
		
		json.personId = medicine.getPerson().getId();
		return json;
	}
	
	/**
	 * @param medicineId
	 * @return
	 */
	@PutMapping(path = "/delete")
	public Response<Void> delete(@RequestParam(value="medicineId") Long medicineId) {
		Medicine model = null;
		
		if(medicineId == null) {
			return new Response<Void>(false);
		}
		
		model =	medicineRepository.findById(medicineId).orElse(null);
		
		if(model == null) {
			return new Response<Void>(false);
		}
		
		medicineService.delete(model);
		
		return new Response<Void>(true);
	}
	
	@PostMapping(path = "/save/multi")
	public Response<Void> save(@RequestBody List<it.paolomolteni.farmacobackend.json.Medicine> medicines) {
		
		List<Medicine> medicinesToSave = new ArrayList<Medicine>();
		Medicine medicineTmp = null;
		
		for(it.paolomolteni.farmacobackend.json.Medicine medicine : medicines) {
			medicineTmp = null;
			
			if(medicine.id != null) {
				medicineTmp =	medicineRepository.findById(medicine.id).orElse(null);
			}
			
			if(medicineTmp == null) {
				medicineTmp = new Medicine();
			}
			
			medicineTmp.setDate(new Date(medicine.tsDate));
			
			if(medicine.tsDateExpiry != null) {
				medicineTmp.setDateExpiry(new Date(medicine.tsDateExpiry));
			}
			
			if(medicine.tsDateExpiryWhenOpened != null) {
				medicineTmp.setDateExpiryWhenOpened(new Date(medicine.tsDateExpiryWhenOpened));
			}
			
			medicineTmp.setName(medicine.name);
			medicineTmp.setDescription(medicine.description);
			medicineTmp.setCause(medicine.cause);
			
			Person person = this.personRepository.findById(medicine.personId).orElse(null);
			medicineTmp.setPerson(person);
			
			medicinesToSave.add(medicineTmp);
		}
		
		medicineService.save(medicinesToSave);
		
		return new Response<Void>(true);
		
	}

}
