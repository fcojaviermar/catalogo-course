package es.catalogo.courses.web;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import es.catalogo.courses.CatalogueCoursesApplication;
import es.catalogo.courses.web.dto.CourseDTO;

@RunWith(SpringRunner.class)
@SpringBootTest (classes = CatalogueCoursesApplication.class)
public class FindCoursesIT {

	@Autowired
	CourseController courseController;
	
	@Test
	@Sql("/courses.sql")
	public void shouldFindAll() {
		ResponseEntity<Page<CourseDTO>> result = courseController.findAll(0, 5, true);
		assertEquals(result.getBody().getContent().size(), 5);
	}
	
	

	@Test
	@Sql("/courses.sql")
	public void shouldFindFirstPageThreeCourses() {
		ResponseEntity<Page<CourseDTO>> result = courseController.findAll(0, null, true);
		assertEquals(result.getBody().getContent().size(), 3);
	}

	
	@Test
	@Sql("/courses.sql")
	public void shouldFindTwoPageLastPageTwoCourses() {
		ResponseEntity<Page<CourseDTO>> result = courseController.findAll(0, null, true);
		result = courseController.findAll(result.getBody().getPageable().next().getPageNumber(), 
										  null, true);
		assertEquals(result.getBody().getContent().size(), 3);//3 is the defect value.
	}
	
	
	@Test
	@Sql("/coursesFalse.sql")
	public void shouldNotFindCourses() {
		ResponseEntity<Page<CourseDTO>> result = courseController.findAll(0, null, true);
		assertEquals(result.getBody().getContent().size(), 0);
	}
	
	
	@Test
	@Sql("/courses.sql")
	public void shouldFindThreeElements1() {
		ResponseEntity<Page<CourseDTO>> result = courseController.findAll(null, null, true);
		assertEquals(result.getBody().getContent().size(), 3);
	}
	
	@Test
	@Sql("/courses.sql")
	public void shouldFindThreeElements2() {
		ResponseEntity<Page<CourseDTO>> result = courseController.findAll(0, null, true);
		assertEquals(result.getBody().getContent().size(), 3);
	}	
	
	
	@Test
	@Sql("/courses.sql")
	public void shouldFindThreeElements3() {
		ResponseEntity<Page<CourseDTO>> result = courseController.findAll(null, 1, true);
		assertEquals(result.getBody().getContent().size(), 3);
	}
	
	@Test
	public void shouldFindZeroElements() {
		ResponseEntity<Page<CourseDTO>> result = courseController.findAll(null, null, true);
		
		assertEquals(result.getStatusCode(), HttpStatus.NO_CONTENT);
		assertEquals(result.getBody().getContent().size(), 0);
	}
}
