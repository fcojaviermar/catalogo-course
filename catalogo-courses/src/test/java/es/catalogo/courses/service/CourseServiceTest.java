package es.catalogo.courses.service;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

import es.catalogo.courses.entity.Course;
import es.catalogo.courses.enums.Level;
import es.catalogo.courses.exception.NoContentException;
import es.catalogo.courses.repository.CourseRepository;
import es.catalogo.courses.service.impl.CourseServiceImpl;
import es.catalogo.courses.web.dto.CourseDTO;
import es.catalogo.teachers.web.dto.PageImplResponse;


@RunWith(MockitoJUnitRunner.class)
public class CourseServiceTest {

//	@InjectMocks //The service I want to test.
	private CourseServiceImpl courseService;

	
	@Mock //The service dependencies
	private CourseRepository courseRepository;

	
	@Before
	public void setUp() {
		courseService = new CourseServiceImpl(courseRepository, 0, 5);
	}
	
	
	@Test
	public void shouldCreateCourse() {
		CourseDTO inputCourse = new CourseDTO(false, 5, "Micro", 20, Level.HIGH);
		
		Course saveResult = new Course(2, true, 5, "Eclipse", 45, Level.HIGH);
		CourseDTO expectedResult = new CourseDTO(true, 5, "Eclipse", 45, Level.HIGH);

		when(courseRepository.save(new Course(inputCourse))).thenReturn(saveResult);
		ResponseEntity<CourseDTO> result = courseService.add(inputCourse);
		
		assertTrue(result.getBody().equals(expectedResult));
	}
	
	
	@Test
	public void shouldFindCourses() {
		List<Course> listReturn = new ArrayList<>();
		listReturn.add(new Course(1, true, 1, "Micro", 40, Level.BASIC));
		listReturn.add(new Course(2, true, 2, "Eclipse", 20, Level.MEDIUM));
		Page<Course> pagedReturn = new PageImpl<Course>(listReturn);
		
		List<CourseDTO> listResult = new ArrayList<>();
		listResult.add(new CourseDTO(true, 1, "Micro", 40, Level.BASIC));
		listResult.add(new CourseDTO(true, 2, "Eclipse", 20, Level.MEDIUM));

		
		Pageable firstPageWithTwoElements = PageRequest.of(0, 5);		
		when(courseRepository.findAllByActive(firstPageWithTwoElements, true)).thenReturn(pagedReturn);
		try {		
			ResponseEntity<PageImplResponse<CourseDTO>> result = courseService.findAll(0, 5, true);
			
			assertTrue(result.getBody().getContent().containsAll(listResult));
			
		} catch (NoContentException e) {
			fail("Exception");
		}			

	}
	
	@Test
	public void shouldFindCourses2() {
		List<Course> listReturn = new ArrayList<>();
		listReturn.add(new Course(1, true, 1, "Micro", 40, Level.BASIC));
		listReturn.add(new Course(2, true, 2, "Eclipse", 20, Level.MEDIUM));
		Page<Course> pagedReturn = new PageImpl<Course>(listReturn);
		
		List<CourseDTO> listResult = new ArrayList<>();
		listResult.add(new CourseDTO(true, 1, "Micro", 40, Level.BASIC));
		listResult.add(new CourseDTO(true, 2, "Eclipse", 20, Level.MEDIUM));

		Pageable firstPageWithTwoElements = PageRequest.of(0, 5);		
		when(courseRepository.findAllByActive(firstPageWithTwoElements, true)).thenReturn(pagedReturn);
		
		try {
			ResponseEntity<PageImplResponse<CourseDTO>> result = courseService.findAll(0, 5, true);
			
			assertTrue(result.getBody().getContent().containsAll(listResult));
			
		} catch (NoContentException e) {
			fail("Exception");
		}			
	}
	
}
