package it.paolomolteni.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import it.paolomolteni.pojo.Person;

public class PersonDaoImpl implements PersonDao {
	
	@PersistenceContext(unitName="unit1")
	EntityManager em;

	/* (non-Javadoc)
	 * @see it.paolomolteni.dao.PersonDao#createPerson(java.lang.String, java.lang.String, java.lang.Integer)
	 */
	@Override
	public Person createPerson(String name, String lastName, Integer age) {
		Person person = new Person();
		person.setName(name);
		person.setLastName(lastName);
		person.setAge(age);
		
		return em.merge(person);
	}

	@Override
	public List<Person> getAllPersons() {
		String queryS = "select p from Person p";
		
		Query query = em.createQuery(queryS);
		
		return query.getResultList();
		
	}

	@Override
	public Person get(long personId) {
		String queryS = "select p from Person p left join fetch p.cars where p.id = :id";
		
		Query query = em.createQuery(queryS);
		query.setParameter("id", personId);
		
		return (Person) query.getSingleResult();
	}

}
