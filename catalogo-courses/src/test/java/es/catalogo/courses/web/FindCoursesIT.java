package es.catalogo.courses.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import es.catalogo.courses.CatalogueCoursesApplication;
import es.catalogo.courses.exception.NoContentException;
import es.catalogo.courses.web.dto.CourseDTO;
import es.catalogo.teachers.web.dto.PageImplResponse;

@RunWith(SpringRunner.class)
@SpringBootTest (classes = CatalogueCoursesApplication.class)
public class FindCoursesIT {

	@Autowired
	CourseController courseController;
	
	@Test
	@Sql("/courses.sql")
	public void shouldFindAll() {
		ResponseEntity<PageImplResponse<CourseDTO>> result = null;
		try {
			result = courseController.findAll(0, 5, true);
			assertEquals(5, result.getBody().getContent().size());
			
		} catch (NoContentException e) {
			fail("Exception");
		}

	}
	
	

	@Test
	@Sql("/courses.sql")
	public void shouldFindFirstPageThreeCourses() {
		try {
			ResponseEntity<PageImplResponse<CourseDTO>> result = courseController.findAll(0, null, true);
			assertEquals(3, result.getBody().getContent().size());
			
		} catch (NoContentException e) {
			fail("Exception");
		}			
	}

	
	@Test
	@Sql("/courses.sql")
	public void shouldFindTwoPageLastPageTwoCourses() {
		try {
			ResponseEntity<PageImplResponse<CourseDTO>> result = courseController.findAll(0, null, true);
			result = courseController.findAll(result.getBody().getPageable().next().getPageNumber(), 
											  null, true);
			assertEquals(3, result.getBody().getContent().size());//3 is the defect value.
			
		} catch (NoContentException e) {
			fail("Exception");
		}			
	}
	
	
	@Test
	@Sql("/coursesFalse.sql")
	public void shouldNotFindCourses() {
		try {
			courseController.findAll(0, null, true);
			fail("Exception");
			
		} catch (NoContentException e) {
			assertTrue("Correct exception", true);
		}		
	}
	
	
	@Test
	@Sql("/courses.sql")
	public void shouldFindThreeElements1() {
		try {
			ResponseEntity<PageImplResponse<CourseDTO>> result = courseController.findAll(null, null, true);
			assertEquals(3, result.getBody().getContent().size());
			
		} catch (NoContentException e) {
			fail("Exception");
		}			
	}
	
	
	@Test
	@Sql("/courses.sql")
	public void shouldFindThreeElements2() {
		try {
			ResponseEntity<PageImplResponse<CourseDTO>> result = courseController.findAll(0, null, true);
			assertEquals(3, result.getBody().getContent().size());
			
		} catch (NoContentException e) {
			fail("Exception");
		}			
	}	
	
	
	@Test
	@Sql("/courses.sql")
	public void shouldFindThreeElements3() {
		try {
			ResponseEntity<PageImplResponse<CourseDTO>> result = courseController.findAll(null, 1, true);
			assertEquals(3, result.getBody().getContent().size());
			
		} catch (NoContentException e) {
			fail("Exception");
		}			
	}
	
	@Test
	@Sql("/delete.sql")
	public void shouldFindZeroElements() {
		try {
			courseController.findAll(null, null, true);
		
			fail("Exception");
			
		} catch (NoContentException e) {
			assertTrue("Correct exception", true);
		}
		

	}
}
