package es.catalogo.courses.parent;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import es.catalogo.courses.handler.CustomizedCatalogoExceptionHandler;
import es.catalogo.courses.web.CourseController;


public class ParentStep {

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
	
	protected MvcResult postCall(final String serviceCall, String contentToSend) throws Exception {
		MvcResult result = null;

		mockWithControllerAdvice();

		result = mockMvc.perform(post(serviceCall).content(contentToSend).
				 		 contentType(MediaType.APPLICATION_JSON)).
		         		 andReturn();
		
		return result;
	}

	
	protected MvcResult getCall(final String serviceCall) throws Exception {
		MvcResult result = null;
		
		mockWithControllerAdvice();
		
		result = mockMvc.perform(get(serviceCall)
								.contentType(MediaType.APPLICATION_JSON))
								.andReturn();

		return result;
	}

	public static ObjectMapper getJsonMapper() {
		return JSON_MAPPER;
	}
}
