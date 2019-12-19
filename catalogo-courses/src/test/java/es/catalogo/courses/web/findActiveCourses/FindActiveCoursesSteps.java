package es.catalogo.courses.web.findActiveCourses;


import java.net.URI;
import java.net.URISyntaxException;

import org.junit.runner.RunWith;
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
@ContextConfiguration(classes = CatalogueCoursesApplication.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@Sql("/courses.sql")
public class FindActiveCoursesSteps {

	private PageImplResponse<?> page = null;
	
	@LocalServerPort
    int randomServerPort;
	
    @Given("^un catalogo de cursos disponibles en el sistema$")
    public void a_transaction_that_is_stored_in_our_system() {
    	RestTemplate restTemplate = new RestTemplate();
	     
	    final String baseUrl = "http://localhost:" +randomServerPort+"/courses?page=0&size=1&active=true";
	    URI uri = null;
		try {
			uri = new URI(baseUrl);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 
	    CourseDTO courseDTO = new CourseDTO(true, 1, "Title", 30, Level.BASIC);
	    
	    HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<CourseDTO> requestEntity = new HttpEntity<CourseDTO>(courseDTO, headers);
	    
        restTemplate.postForEntity(uri, requestEntity, CourseDTO.class);
    }

    
	@When("^accedo a los cursos activos$")
	public void i_check_the_status_from_internal_channel() throws Throwable{
		
		RestTemplate restTemplate = new RestTemplate();
	     
	    final String baseUrl = "http://localhost:" +randomServerPort+"/courses?page=0&size=1&active=true";
	    URI uri = new URI(baseUrl);
	 
    
	    page = restTemplate.getForObject(uri, PageImplResponse.class);
	    
	}

	
	@Then("^el sistema devuelve la lista de cursos activos$")
	public void the_system_returns_the_status_SETTLED() throws Throwable {
//		
	}

}
