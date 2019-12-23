package it.paolomolteni.genericcrud.rest;

import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.paolomolteni.genericcrud.CrudException;
import it.paolomolteni.genericcrud.json.ICrudModel;
import it.paolomolteni.genericcrud.json.Response;


public abstract class GenericController<J extends ICrudModel, M> {

	/**
	 * 
	 */
	protected Class<M> modelClass;
	
	/**
	 * 
	 */
	protected Class<J> jsonClass;
	
	/**
	 * @return
	 */
	public abstract CrudRepository<M, Long> getRepository();
	
	/**
	 * 
	 */
	public abstract M mapJsonToModel(J json);
	
	/**
	 * @param model
	 * @return
	 */
	public abstract J mapModelToJson(M model);
	
	/**
	 * @param modelClass
	 * @param jsonClass
	 */
	public GenericController(Class<M> modelClass, Class<J> jsonClass) {
		super();
		this.modelClass = modelClass;
		this.jsonClass = jsonClass;
	}
	
	/**
	 * @param json
	 * @return
	 * @throws CrudException
	 */
	@PostMapping("/insert")
	public Response<J> saveEntity(@RequestBody J json) throws CrudException {
		M entity = null;
		
		if(json.getId() != null) {
			entity = getRepository().findById(json.getId()).orElse(null);
			
			if(entity == null) {
				return Response.error(jsonClass);
			}
		}
		else {
			try {
				entity = modelClass.newInstance();
			} 
			catch (Exception e) {
				throw new CrudException(e);
			} 
		}
		
		entity = mapJsonToModel(json);
		
		entity = getRepository().save(entity);
		
		return Response.ok(mapModelToJson(entity));
	}
	
	/**
	 * @param id
	 * @return
	 */
	@RequestMapping("/get")
	public Response<J> getEntity(@RequestParam(value="id") Long id){
		
		M entity = getRepository().findById(id).orElse(null);
		
		if(entity == null) {
			return Response.error(jsonClass);
		}
		
		return Response.ok(mapModelToJson(entity));
	}
	
	/**
	 * @param id
	 * @return
	 */
	@GetMapping("/delete")
    public Response<J> deleteEntity(@RequestParam(value="id") Long id) {
		
		M entity = getRepository().findById(id).orElse(null);
		
		if(entity == null) {
			return Response.error(jsonClass);
		}
		
		getRepository().delete(entity);
		
		return Response.ok(jsonClass);
    }

}
