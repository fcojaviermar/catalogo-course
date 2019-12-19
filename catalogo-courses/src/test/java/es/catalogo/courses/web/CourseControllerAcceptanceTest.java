package es.catalogo.courses.web;

import static org.assertj.core.api.Assertions.fail;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import es.catalogo.courses.CatalogueCoursesApplication;
import es.catalogo.courses.enums.Level;
import es.catalogo.courses.handler.CustomizedCatalogoExceptionHandler;
import es.catalogo.courses.web.dto.CourseDTO;

@RunWith(SpringRunner.class)
@SpringBootTest (classes = CatalogueCoursesApplication.class)
@AutoConfigureMockMvc
public class CourseControllerAcceptanceTest {

	
	@Autowired
	private CourseController controller;

	@Autowired
    private MockMvc mockMvc;
	
	private static final ObjectMapper JSON_MAPPER = new ObjectMapper();
	
	
	private void mockWithControllerAdvice() {
		mockMvc = MockMvcBuilders.standaloneSetup(controller).
				  setControllerAdvice(new CustomizedCatalogoExceptionHandler()).
				  build();
	}
	
	private MvcResult postCall(final String serviceCall, String contentToSend) throws Exception {
		MvcResult result = null;

		mockWithControllerAdvice();

		result = mockMvc.perform(post(serviceCall).content(contentToSend).
				 		 contentType(MediaType.APPLICATION_JSON)).
		         		 andReturn();
		
		return result;
	}

	
	private MvcResult getCall(final String serviceCall) throws Exception {
		MvcResult result = null;
		
		mockWithControllerAdvice();
		
		result = mockMvc.perform(get(serviceCall)
								.contentType(MediaType.APPLICATION_JSON))
								.andReturn();

		return result;
	}
	
	
	@Test
	@Sql("/courses.sql")
	public void errorTitleNull() {
		try {
			CourseDTO courseDTO = new CourseDTO(true, 1, null, 40, Level.BASIC);
			
			MvcResult result = postCall("/courses", JSON_MAPPER.writeValueAsString(courseDTO));
			
			assertTrue(String.valueOf(result.getResponse().getStatus()), 
									  result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value());
			
		} catch (Exception e) {
			fail("Exception not controlled");
		}
	}
	
	
	@Test
	@Sql("/delete.sql")
	public void noCourses() {
		try {
			MvcResult result = getCall("/courses?page=0&size=1&active=true");
			
			assertTrue(String.valueOf(result.getResponse().getStatus()), result.getResponse().getStatus() == HttpStatus.NO_CONTENT.value());
		} catch (Exception e) {
			fail("Exception not controlled");
		}
	}
	
//	@Test
//	public void addCourseTeacherNotExist() {
//		try {
//			CourseDTO courseDTO = new CourseDTO(true, 1, "Pedro", 40, Level.BASIC);
//			
//			MvcResult result = postCall("/courses", JSON_MAPPER.writeValueAsString(courseDTO));
//			
//			assertTrue(String.valueOf(result.getResponse().getStatus()), 
//									  result.getResponse().getStatus() == HttpStatus.PRECONDITION_REQUIRED.value());
//			
//		} catch (Exception e) {
//			fail("Exception not controlled");
//		}
//	}
	
	
		
}
