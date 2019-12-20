package es.catalogo.courses.web.findActiveCourses;


import static org.junit.Assert.assertTrue;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.stream.IntStream;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
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
public class FindActiveCoursesSteps {

	private PageImplResponse<?> page = null;
	
	@LocalServerPort
    private int randomServerPort;
	
	
	
    @Given("^un catalogo de cursos disponibles en el sistema$")
    public void a_transaction_that_is_stored_in_our_system() {
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
        
        
//        System.out.println(IntStream.range(1, 5)  
//        .filter(i -> i % 2 == 0)
//        .allMatch(i -> i % 2 == 0));

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
	public void i_check_the_status_from_internal_channel() throws Throwable{
		
		RestTemplate restTemplate = new RestTemplate();
	     
	    final String baseUrl = "http://localhost:" +randomServerPort+"/courses?page=0&size=10&active=true";
	    URI uri = new URI(baseUrl);
	 
    
	    page = restTemplate.getForObject(uri, PageImplResponse.class);
	    
	}

	
	@Then("^el sistema devuelve la lista de cursos activos$")
	public void the_system_returns_the_status_SETTLED() throws Throwable {
		assertTrue(String.valueOf(page.getContent().size()), page.getContent().size() == 8);
	}

}
