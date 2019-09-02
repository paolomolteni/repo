package it.paolomolteni.farmacobackend;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.paolomolteni.farmacobackend.model.Farmaco;


@Repository
public interface FarmacoRepository extends CrudRepository<Farmaco, Long> {


	/**
	 * @return
	 */
	@Query(value = "SELECT f FROM it.paolomolteni.farmacobackend.model.Farmaco f order by f.id")
	public List<Farmaco> getFarmaci();
	
}
