package it.paolomolteni.farmacobackend;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.paolomolteni.farmacobackend.model.Medicine;


@Repository
public interface MedicineRepository extends CrudRepository<Medicine, Long> {


	/**
	 * @return
	 */
	@Query(value = "SELECT m FROM it.paolomolteni.farmacobackend.model.Medicine m order by m.id")
	public List<Medicine> getMedicines();
	
}
