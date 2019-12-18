package es.catalogo.courses;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//This annotation contains @Configuration, @EnableAutoConfiguration and @ComponentScan annotations.
@SpringBootApplication 
public class CatalogueCoursesApplication {

	public static void main(String[] args) {
		SpringApplication.run(CatalogueCoursesApplication.class, args);
	}

}
