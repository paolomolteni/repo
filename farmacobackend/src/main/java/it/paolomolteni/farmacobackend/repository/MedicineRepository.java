package it.paolomolteni.farmacobackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.paolomolteni.farmacobackend.model.Medicine;
import it.paolomolteni.farmacobackend.model.Person;


@Repository
public interface MedicineRepository extends CrudRepository<Medicine, Long> {


	/**
	 * @return
	 */
	@Query(value = "SELECT m FROM it.paolomolteni.farmacobackend.model.Medicine m order by m.id")
	public List<Medicine> getMedicines();
	
	/**
	 * @return
	 */
	@Query(value = "SELECT m FROM it.paolomolteni.farmacobackend.model.Medicine m where m.person = :person order by m.id")
	public List<Medicine> getMedicinesByPerson(@Param("person") Person person);
	
}
