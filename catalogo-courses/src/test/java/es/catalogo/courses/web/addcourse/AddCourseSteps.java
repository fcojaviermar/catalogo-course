package es.catalogo.courses.web.addcourse;


import static org.junit.Assert.assertTrue;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import es.catalogo.courses.CatalogueCoursesApplication;
import es.catalogo.courses.enums.Level;
import es.catalogo.courses.web.dto.CourseDTO;


@RunWith(SpringRunner.class)
@ContextConfiguration(classes = CatalogueCoursesApplication.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class AddCourseSteps {

	@LocalServerPort
    private int randomServerPort;
	
	private ResponseEntity<CourseDTO> responseEntity = null;
	private CourseDTO courseDTO = null;
	
	
    @Given("^un curso que no esta en el sistema$")
    public void curso_no_en_sistema() {
    	courseDTO = new CourseDTO(true, 1, "Title", 40, Level.BASIC);
    }
    
	
    @When("^anado el curso al sistema$")
	public void anado_el_curso() throws Throwable{
				
		RestTemplate restTemplate = new RestTemplate();
	     
	    final String baseUrl = "http://localhost:" +randomServerPort+"/courses";
	    URI uri = null;
		try {
			uri = new URI(baseUrl);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 
	    HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<CourseDTO> requestEntity = new HttpEntity<CourseDTO>(courseDTO, headers);
	    
        responseEntity = restTemplate.postForEntity(uri, requestEntity, CourseDTO.class);
	}

	
	@Then("^el curso se anade correctamente$")
	public void curso_anadido() throws Throwable {
		assertTrue(String.valueOf(responseEntity.getStatusCodeValue()), responseEntity.getStatusCodeValue() == HttpStatus.OK.value());	
	}

}