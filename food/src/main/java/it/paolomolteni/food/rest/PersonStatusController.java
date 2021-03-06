package it.paolomolteni.food.rest;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.paolomolteni.food.json.QualityPerDay;
import it.paolomolteni.food.json.Response;
import it.paolomolteni.food.model.Person;
import it.paolomolteni.food.model.PersonStatus;
import it.paolomolteni.food.repository.PersonRepository;
import it.paolomolteni.food.repository.PersonStatusRepository;
import it.paolomolteni.food.service.PersonStatusService;
import it.paolomolteni.food.utility.MappingEntity;

@RestController
@RequestMapping("/person/status")
public class PersonStatusController {
	
	/**
	 * 
	 */
	@Autowired
	private PersonStatusService personStatusService;
	
	/**
	 * 
	 */
	@Autowired
	private PersonStatusRepository personStatusRepository;
	
	/**
	 * 
	 */
	@Autowired
	private PersonRepository personRepository;
	
	/**
	 * http://localhost:8080/food/person/status/insert
	 * 
	 * @param model
	 * @return
	 */
	@PostMapping("/insert")
    public Response<it.paolomolteni.food.json.PersonStatus> save(@RequestBody it.paolomolteni.food.json.PersonStatus model) {
		
		PersonStatus personStatus = null;
		
		if(model.getId() != null) {
			personStatus = personStatusRepository.findById(model.getId());
			
			if(personStatus == null) {
				return Response.error(it.paolomolteni.food.json.PersonStatus.class);
			}
		}
		else {
			personStatus = new PersonStatus();
		}
		
		if(model.getIdPerson() == null) {
			return Response.error(it.paolomolteni.food.json.PersonStatus.class);
		}
		
		Person person = personRepository.findById(model.getIdPerson());
		
		if(person == null) {
			return Response.error(it.paolomolteni.food.json.PersonStatus.class);
		}
		
		personStatus.setDate(model.getDate());
		personStatus.setDescription(model.getDescription());
		personStatus.setPerson(person);
		personStatus.setQuality(model.getQuality());
		
		personStatus = personStatusService.save(personStatus);
		
		return Response.ok(MappingEntity.mapPersonStatus(personStatus));
    }
	
	/**
	 * http://localhost:8080/food/person/status/get/byperson?idPerson=1
	 * 
	 * @param idPerson
	 * @return
	 */
	@RequestMapping("/get")
    public Response getByPerson(@RequestParam(value="idPerson", required = false) Long idPerson) {

		List<PersonStatus> status = new ArrayList<PersonStatus>();
		
		if(idPerson != null) {
			Person person = personRepository.findById(idPerson);
			
			if(person == null) {
				return Response.error();
			}
			
			status.addAll(personStatusRepository.findByPerson(person));
		}
		else {
			personStatusRepository.findAll().forEach(status::add); 
		}
		
		List<it.paolomolteni.food.json.PersonStatus> modelFoods = status.stream().map(s -> MappingEntity.mapPersonStatus(s)).collect(Collectors.toList());
		
		return Response.ok(modelFoods);
    }
	
	/**
	 * http://localhost:8080/food/person/status/delete?id=1
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/delete")
    public Response<it.paolomolteni.food.json.PersonStatus> deletePerson(@RequestParam(value="id") Long id) {
		
		PersonStatus personStatus = personStatusRepository.findById(id);
		
		if(personStatus == null) {
			return Response.error(it.paolomolteni.food.json.PersonStatus.class);
		}
		
		personStatusService.delete(personStatus);
		
		return Response.ok(it.paolomolteni.food.json.PersonStatus.class);
    }
	
	/**
	 * @param idPerson
	 * @return
	 */
	@GetMapping("/get/quality")
    public Response getQualities(@RequestParam(value="idPerson", required = false) Long idPerson) {
		
		Person person = null;
		
		if(idPerson != null) {
			person = personRepository.findById(idPerson);
		}
		
		if(person == null) {
			return Response.error();
		}
		
		List<Object[]> qualityDayByDay = personStatusRepository.getQualityDayByDay(person);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		
		List<QualityPerDay> qualityPerDays = qualityDayByDay.stream().map(item -> new QualityPerDay(sdf.format(item[0]), (Integer)item[1])).collect(Collectors.toList());
		
		return Response.ok(qualityPerDays);
    }

}
