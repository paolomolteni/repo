package it.paolomolteni.farmacobackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class App extends SpringBootServletInitializer  {
    
	/**
	 * @param args
	 * on Tomcat: http://localhost:8080/farmacobackend
	 * 
	 */
	public static void main( String[] args ){
		SpringApplication.run(App.class);
    }
}

