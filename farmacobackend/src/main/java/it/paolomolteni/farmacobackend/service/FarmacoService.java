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

import it.paolomolteni.farmacobackend.FarmacoRepository;
import it.paolomolteni.farmacobackend.model.Farmaco;

@CrossOrigin
@RestController
@RequestMapping("/farmaco")
public class FarmacoService {
	
	/**
	 * 
	 */
	@Autowired
	private FarmacoRepository farmacoRepository;
	
	/**
	 * 
	 */
	private final String DATE_FORMAT = "yyyy-MM-dd";
	
	/**
	 * @return
	 * http://localhost:8888/farmaco/list
	 */
	@GetMapping(path = "/list")
    public List<it.paolomolteni.farmacobackend.json.Farmaco> getList() {
		List<it.paolomolteni.farmacobackend.json.Farmaco> list = new ArrayList<it.paolomolteni.farmacobackend.json.Farmaco>();
		List<Farmaco> farmaci = farmacoRepository.getFarmaci();

		for(Farmaco farmaco : farmaci) {
			list.add(mapFarmaco(farmaco));
		}
		
		return list;
    }
	
	/**
	 * @param farmaco
	 * http://localhost:8888/farmaco/save
	 */
	@PostMapping(path = "/save")
	public it.paolomolteni.farmacobackend.json.Farmaco save(@RequestBody it.paolomolteni.farmacobackend.json.Farmaco farmaco) {
		Farmaco model = null;
		
		if(farmaco.id != null) {
			model =	farmacoRepository.findById(farmaco.id).orElse(null);
		}
		
		if(model == null) {
			model = new Farmaco();
		}

		try {
			model.setData(getDateFromString(farmaco.data));
			model.setDataScadenza(getDateFromString(farmaco.dataScadenza));
			model.setDataScadenzaAperto(getDateFromString(farmaco.dataScadenzaAperto));
		} 
		catch (Exception e) {
			
		}
		
		model.setNomeFarmaco(farmaco.nomeFarmaco);
		model.setDescrizione(farmaco.descrizione);
		
		model = farmacoRepository.save(model);
		
		return mapFarmaco(model);
	}
	
	/**
	 * @param farmaco
	 * @return
	 */
	private it.paolomolteni.farmacobackend.json.Farmaco mapFarmaco(Farmaco farmaco){
		return new it.paolomolteni.farmacobackend.json.Farmaco(farmaco.getId(), getFormettedDate(farmaco.getData()), farmaco.getNomeFarmaco(), farmaco.getDescrizione(),
				getFormettedDate(farmaco.getDataScadenza()), getFormettedDate(farmaco.getDataScadenzaAperto()));
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
