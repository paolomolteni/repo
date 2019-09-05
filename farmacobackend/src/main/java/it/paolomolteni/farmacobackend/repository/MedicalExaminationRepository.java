package it.paolomolteni.farmacobackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.paolomolteni.farmacobackend.model.MedicalExamination;
import it.paolomolteni.farmacobackend.model.Person;

@Repository
public interface MedicalExaminationRepository extends CrudRepository<MedicalExamination, Long> {
	
	/**
	 * @param person
	 * @return
	 */
	@Query(value = "SELECT me FROM it.paolomolteni.farmacobackend.model.MedicalExamination me where me.person = :person order by me.id")
	public List<MedicalExamination> getExaminationByPerson(@Param("person") Person person);
	
	/**
	 * @return
	 */
	@Query(value = "SELECT me FROM it.paolomolteni.farmacobackend.model.MedicalExamination me order by me.id")
	public List<MedicalExamination> getExamination();

}
