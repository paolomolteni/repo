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
import it.paolomolteni.farmacobackend.model.MedicalExamination;
import it.paolomolteni.farmacobackend.model.Person;
import it.paolomolteni.farmacobackend.repository.MedicalExaminationRepository;
import it.paolomolteni.farmacobackend.repository.PersonRepository;
import it.paolomolteni.farmacobackend.service.MedicalExaminationService;

@CrossOrigin
@RestController
@RequestMapping("/medicalexamination")
public class MedicalExaminationRestService {
	
	/**
	 * 
	 */
	@Autowired
	private MedicalExaminationRepository medicalExaminationRepository;
	
	/**
	 * 
	 */
	@Autowired
	private PersonRepository personRepository;
	
	/**
	 * 
	 */
	@Autowired
	private MedicalExaminationService medicalExaminationService;
	
	/**
	 * @return
	 * http://localhost:8888/medicalexamination/list
	 */
	@GetMapping(path = "/list")
    public List<it.paolomolteni.farmacobackend.json.MedicalExamination> getList() {
		List<it.paolomolteni.farmacobackend.json.MedicalExamination> list = new ArrayList<it.paolomolteni.farmacobackend.json.MedicalExamination>();
		List<MedicalExamination> examinations = medicalExaminationRepository.getExamination();

		for(MedicalExamination medicalExamination : examinations) {
			list.add(mapExamination(medicalExamination));
		}
		
		return list;
    }
	
	/**
	 * @param id
	 * @return
	 */
	@GetMapping(path = "/list/person")
    public List<it.paolomolteni.farmacobackend.json.MedicalExamination> getListByPerson(@RequestParam(value="personId") Long personId) {
		List<it.paolomolteni.farmacobackend.json.MedicalExamination> list = new ArrayList<it.paolomolteni.farmacobackend.json.MedicalExamination>();
		
		List<MedicalExamination> examinations = new ArrayList<MedicalExamination>();
		
		if(personId != null) {
			Person person = personRepository.findById(personId).orElse(null);
			
			if(person != null) {
				examinations.addAll(medicalExaminationRepository.getExaminationByPerson(person));
			}
		}

		for(MedicalExamination examination : examinations) {
			list.add(mapExamination(examination));
		}
		
		return list;
    }
	
	/**
	 * @param examination
	 * http://localhost:8888/medicalexamination/save
	 */
	@PostMapping(path = "/save")
	public it.paolomolteni.farmacobackend.json.MedicalExamination save(@RequestBody it.paolomolteni.farmacobackend.json.MedicalExamination examination) {
		MedicalExamination model = null;
		
		if(examination.id != null) {
			model =	medicalExaminationRepository.findById(examination.id).orElse(null);
		}
		
		if(model == null) {
			model = new MedicalExamination();
		}
		
		model.setDate(new Date(examination.tsDate));
		model.setType(examination.type);
		model.setReason(examination.reason);
		model.setPrice(examination.price);
		
		Person person = this.personRepository.findById(examination.personId).orElse(null);
		model.setPerson(person);
		
		model = medicalExaminationRepository.save(model);
		
		return mapExamination(model);
	}
	
	/**
	 * @param examination
	 * @return
	 */
	private it.paolomolteni.farmacobackend.json.MedicalExamination mapExamination(MedicalExamination examination){
		it.paolomolteni.farmacobackend.json.MedicalExamination json = new it.paolomolteni.farmacobackend.json.MedicalExamination(examination.getId(), examination.getDate().getTime(), examination.getType() , examination.getReason(), examination.getPrice(), examination.getPerson().getId());
		return json;
	}
	
	/**
	 * @param examinationId
	 * @return
	 */
	@PutMapping(path = "/delete")
	public Response<Void> delete(@RequestParam(value="examinationId") Long examinationId) {
		MedicalExamination model = null;
		
		if(examinationId == null) {
			return new Response<Void>(false);
		}
		
		model =	medicalExaminationRepository.findById(examinationId).orElse(null);
		
		if(model == null) {
			return new Response<Void>(false);
		}
		
		medicalExaminationService.delete(model);
		
		return new Response<Void>(true);
	}
	
	/**
	 * @param examinations
	 * @return
	 */
	@PostMapping(path = "/save/multi")
	public Response<Void> save(@RequestBody List<it.paolomolteni.farmacobackend.json.MedicalExamination> examinations) {
		
		List<MedicalExamination> examinationsToSave = new ArrayList<MedicalExamination>();
		MedicalExamination examinationTmp = null;
		
		for(it.paolomolteni.farmacobackend.json.MedicalExamination examination : examinations) {
			examinationTmp = null;
			
			if(examination.id != null) {
				examinationTmp =	medicalExaminationRepository.findById(examination.id).orElse(null);
			}
			
			if(examinationTmp == null) {
				examinationTmp = new MedicalExamination();
			}
			
			examinationTmp.setDate(new Date(examination.tsDate));
			examinationTmp.setType(examination.type);
			examinationTmp.setReason(examination.reason);
			examinationTmp.setPrice(examination.price);
			
			Person person = this.personRepository.findById(examination.personId).orElse(null);
			examinationTmp.setPerson(person);
			
			examinationsToSave.add(examinationTmp);
		}
		
		medicalExaminationService.save(examinationsToSave);
		
		return new Response<Void>(true);
	}

}
