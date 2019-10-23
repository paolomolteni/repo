package it.paolomolteni;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import it.paolomolteni.model.Person;
import it.paolomolteni.service.PersonService;

@RunWith(Arquillian.class)
public class PersonServiceTest {
	
	@Inject
	private PersonService personService;
	
	@Deployment
	public static WebArchive createTestArchive() {
		WebArchive jar =  ShrinkWrap.create(WebArchive.class)
				.addPackages(true, "it.paolomolteni")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
		
		System.out.println(jar.toString(true));
		return jar;
	}
	
	/**
	 * 
	 */
	@Test
	public void personTest() {
		Person person = personService.getTestPerson();
		Assert.assertEquals("Paolo", person.getName());
	}
	

}
