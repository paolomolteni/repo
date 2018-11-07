package it.paolomolteni.configuration;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import io.swagger.jaxrs.config.BeanConfig;
import it.paolomolteni.service.CarService;

@ApplicationPath("/api")
public class JaxRsActivator extends Application {
	
	public JaxRsActivator() {
        init();
    }

	private void init() {
	       
        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion("1.0.0");
        beanConfig.setSchemes(new String[]{"http"});
        beanConfig.setHost("localhost:8080");
        beanConfig.setBasePath("/people-registry/api");
        beanConfig.setResourcePackage(CarService.class.getPackage().getName());
        beanConfig.setTitle("RESTEasy, Swagger and Swagger UI Example");
        beanConfig.setDescription("Sample RESTful API built using RESTEasy, Swagger and Swagger UI");
        beanConfig.setScan(true);
  }
	
}
