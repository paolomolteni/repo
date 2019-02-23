package it.paolomolteni.soaptest.service;

import javax.jws.WebService;

import it.paolomolteni.soaptest.model.Message;
import it.paolomolteni.soaptest.model.Person;

@WebService(targetNamespace = "http://personservice/")
public interface PersonService {
	
	public Message greeting(Person person);

}
