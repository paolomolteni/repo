package it.paolomolteni.soaptest.service;

import javax.jws.WebMethod;
import javax.jws.WebService;

import it.paolomolteni.soaptest.model.Message;
import it.paolomolteni.soaptest.model.Person;

@WebService(endpointInterface = "it.paolomolteni.soaptest.service.PersonService",
            targetNamespace = "http://personservice/",
            name = "Person",
            serviceName = "PersonService",
            portName = "PersonPort")
public class PersonServiceImpl {

	/* (non-Javadoc)
	 * @see it.paolomolteni.soaptest.service.PersonService#greeting(it.paolomolteni.soaptest.model.Person)
	 */
	@WebMethod(operationName="greeting")
    public Message greeting(Person person) {
    	Message greeting = new Message();
    	greeting.setMessage(String.format("Hello, %s %s", person.getName(), person.getLastName()));
    	return greeting;
    }
	
}
