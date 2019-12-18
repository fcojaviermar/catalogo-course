package es.catalog.courses.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import es.catalog.courses.service.CourseService;
import es.catalog.courses.web.dto.CourseDTO;


@RunWith(MockitoJUnitRunner.class)
public class CourseControllerTest {
    
	@InjectMocks //The controller I want to test.
	private CourseController courseController;

	
	@Mock //The controller dependencies
    private CourseService courseService;
    
	
	@Test
	public void shouldCreateCourse() {
		CourseDTO input = new CourseDTO(false, 1, "Micro", 40, 3);
		CourseDTO expectedResult = new CourseDTO(true, 2, "Servicio", 40, 2);
		
		when(courseService.add(input)).thenReturn(expectedResult);
	
		CourseDTO result = courseController.add(input);
		
		assertEquals(expectedResult, result);
	}
	
	
	@Test
	public void shouldReturnCourses() {
		List<CourseDTO> listResult = new ArrayList<>();
		listResult.add(new CourseDTO(true, 1, "Micro", 40, 1));
		listResult.add(new CourseDTO(true, 2, "Eclipse", 20, 2));
		Page<CourseDTO> pagedTasks = new PageImpl<CourseDTO>(listResult);
		
		
		when(courseService.findAll(0, 5, true)).thenReturn(pagedTasks);
		Page<CourseDTO> result = courseController.findAll(0, 5, true);
		
		result.forEach(p -> assertTrue(listResult.contains(p)) );
	}
	
	
	@Test
	public void shouldReturnNoCourses() {
		when(courseService.findAll(null, null, true)).thenReturn(new PageImpl<CourseDTO>(new ArrayList<CourseDTO>()));
		Page<CourseDTO> result = courseController.findAll(null, null, true);
		
		//BAD_REQUEST
		assertTrue(result.getContent().size() == 0);
	}
}


