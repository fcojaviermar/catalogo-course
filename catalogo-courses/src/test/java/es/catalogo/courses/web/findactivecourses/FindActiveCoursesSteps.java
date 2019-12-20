package es.catalogo.courses.web.findactivecourses;


import static org.junit.Assert.assertTrue;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.jupiter.api.AfterAll;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import es.catalogo.courses.CatalogueCoursesApplication;
import es.catalogo.courses.enums.Level;
import es.catalogo.courses.web.dto.CourseDTO;
import es.catalogo.teachers.web.dto.PageImplResponse;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = CatalogueCoursesApplication.class)
@AutoConfigureMockMvc
public class FindActiveCoursesSteps {

	private PageImplResponse<?> page = null;
	
	@LocalServerPort
    private int randomServerPort;
	
	
	
    @Given("^un catalogo de cursos disponibles en el sistema$")
    public void aCatalogueWithAvailableCourses() {
    	CourseDTO courseDTO = null;
    	
    	RestTemplate restTemplate = new RestTemplate();
	     
	    final String baseUrl = "http://localhost:" +randomServerPort+"/courses";
	    URI uri = null;
		try {
			uri = new URI(baseUrl);
		} catch (URISyntaxException e) {
		}

	    HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        
//        System.out.println(IntStream.range(1, 5)  //Para todos los números de 1 a 5, sin incluir el 5.
//        							.filter(i -> i % 2 == 0) //Filtro todos los números pares y obtengo un IntStream de números pares
//        							.allMatch(i -> i % 2 == 0)); //Compruebo que todos los números del nuevo IntStream son pares devolviendo true o false si no es correcto.
//
//        System.out.println(IntStream.range(1, 5).reduce(3, (x, y) -> x * y));
        
        for (int i=0; i<15;i++) {
        	if (i%2==0) {
        		courseDTO = new CourseDTO(true, i+1, "Title " + i, 30+i, Level.BASIC);
        	} else {
        		courseDTO = new CourseDTO(false, i+1, "Title " + i, 30+i, Level.BASIC);
        	}
	        HttpEntity<CourseDTO> requestEntity = new HttpEntity<CourseDTO>(courseDTO, headers);
	        restTemplate.postForEntity(uri, requestEntity, CourseDTO.class);
        }
        
        
    }

    
	@When("^accedo a los cursos activos$")
	public void iSearchActiveCourses() throws Throwable{
		
		RestTemplate restTemplate = new RestTemplate();
	     
	    final String baseUrl = "http://localhost:" +randomServerPort+"/courses?page=0&size=10&active=true";
	    URI uri = new URI(baseUrl);
	 
    
	    page = restTemplate.getForObject(uri, PageImplResponse.class);
	    
	}

	
	@Then("^el sistema devuelve la lista de cursos activos$")
	public void theSystemReturnsActiveCourses() throws Throwable {
		assertTrue(String.valueOf(page.getContent().size()), page.getContent().size() == 8);
	}

	
	@AfterAll
	@Sql("/delete.sql")
	public void afterAll() {
	}
}
