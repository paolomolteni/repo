package it.paolomolteni.farmacobackend.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.paolomolteni.farmacobackend.MedicineRepository;
import it.paolomolteni.farmacobackend.model.Medicine;

@CrossOrigin
@RestController
@RequestMapping("/medicine")
public class MedicineService {
	
	/**
	 * 
	 */
	@Autowired
	private MedicineRepository medicineRepository;
	
	/**
	 * 
	 */
	private final String DATE_FORMAT = "yyyy-MM-dd";
	
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

		try {
			model.setDate(getDateFromString(medicine.date));
			model.setDateExpiry(getDateFromString(medicine.dateExpiry));
			model.setDateExpiryWhenOpened(getDateFromString(medicine.dateExpiryWhenOpened));
		} 
		catch (Exception e) {
			
		}
		
		model.setName(medicine.name);
		model.setDescription(medicine.description);
		model.setCause(medicine.cause);
		
		model = medicineRepository.save(model);
		
		return mapMedicine(model);
	}
	
	/**
	 * @param farmaco
	 * @return
	 */
	private it.paolomolteni.farmacobackend.json.Medicine mapMedicine(Medicine medicine){
		return new it.paolomolteni.farmacobackend.json.Medicine(medicine.getId(), getFormettedDate(medicine.getDate()), medicine.getName(), medicine.getDescription(),
				getFormettedDate(medicine.getDateExpiry()), getFormettedDate(medicine.getDateExpiryWhenOpened()), medicine.getCause());
	}
	
	private String getFormettedDate(Date date) {
		if(date != null) {
			DateFormat df = new SimpleDateFormat(DATE_FORMAT);
			return df.format(date);
		}
		
		return null;
	}
	
	private Date getDateFromString(String s) throws Exception {
		
		if(StringUtils.isBlank(s)) {
			return null;
		}
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new SimpleDateFormat(DATE_FORMAT).parse(s));
		calendar.set(Calendar.HOUR_OF_DAY, 2);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

}