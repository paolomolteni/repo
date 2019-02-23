package it.paolomolteni.soaptest.client;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import personservice.Message;
import personservice.Person;
import personservice.PersonService;

public class SoapClientTest {
	
	public static void main(String[] args) throws Exception {
		URL remoteUrl = new URL("http://localhost:8080/soaptest/PersonService?wsdl");
		URL localUrl = SoapClientTest.class.getClassLoader().getResource("PersonService.wsdl");
        QName qname = new QName("http://personservice/", "PersonService");
        Service service = Service.create(localUrl, qname);
        PersonService personService = service.getPort(PersonService.class);
        
        Message message = personService.greeting(new Person() {{
        	this.setName("Mario");
        	this.setLastName("Rossi");
        }});
        
        System.out.println(message.getMessage());
	}

}
