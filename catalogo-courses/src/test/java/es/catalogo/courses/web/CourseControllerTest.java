package es.catalogo.courses.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import es.catalogo.courses.enums.Level;
import es.catalogo.courses.exception.NoContentException;
import es.catalogo.courses.service.CourseService;
import es.catalogo.courses.web.dto.CourseDTO;
import es.catalogo.teachers.web.dto.PageImplResponse;


@RunWith(MockitoJUnitRunner.class)
public class CourseControllerTest {
    
	@InjectMocks //The controller I want to test.
	private CourseController courseController;

	
	@Mock //The controller dependencies
    private CourseService courseService;
    
	
	@Test
	public void shouldCreateCourse() {
		CourseDTO input = new CourseDTO(false, 1, "Micro", 40, Level.HIGH);
		ResponseEntity<CourseDTO> expectedResult = new ResponseEntity<CourseDTO>(new CourseDTO(true, 2, "Servicio", 40, Level.MEDIUM),
																				 HttpStatus.OK);
		
		when(courseService.add(input)).thenReturn(expectedResult);
	
		ResponseEntity<CourseDTO> result = courseController.add(input);
		
		assertEquals(expectedResult, result);
	}
	
	
	@Test
	public void shouldReturnCourses() {
		ResponseEntity<PageImplResponse<CourseDTO>> pagedTasks = null;
		List<CourseDTO> listResult = new ArrayList<>();
		listResult.add(new CourseDTO(true, 1, "Micro", 40, Level.BASIC));
		listResult.add(new CourseDTO(true, 2, "Eclipse", 20, Level.MEDIUM));
		pagedTasks = new ResponseEntity<PageImplResponse<CourseDTO>>(new PageImplResponse<CourseDTO>(listResult),
																	 HttpStatus.OK);

		try {
			when(courseService.findAll(0, 5, true)).thenReturn(pagedTasks);

			ResponseEntity<PageImplResponse<CourseDTO>> result = courseController.findAll(0, 5, true);
			
			assertTrue(result.getBody().getContent().containsAll(listResult));
			
		} catch (NoContentException e) {
			fail("Exception");
		}			
	}
	
	
	@Test
	public void shouldReturnNoCourses() {
		try {
			when(courseService.findAll(null, null, true)).thenReturn(new ResponseEntity<PageImplResponse<CourseDTO>>(new PageImplResponse<CourseDTO>(new ArrayList<CourseDTO>()),
																										 HttpStatus.NO_CONTENT));
			ResponseEntity<PageImplResponse<CourseDTO>> result = courseController.findAll(null, null, true);
			
			assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
			assertTrue(result.getBody().getContent().size() == 0);
			
		} catch (NoContentException e) {
			fail("Exception");
		}			
	}
}


