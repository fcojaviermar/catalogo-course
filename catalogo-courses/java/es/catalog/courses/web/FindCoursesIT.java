package es.catalog.courses.web;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import es.catalog.courses.web.dto.CourseDTO;
import es.catalog.courses.CatalogueCoursesApplication;

@RunWith(SpringRunner.class)
@SpringBootTest (classes = CatalogueCoursesApplication.class)
public class FindCoursesIT {

	@Autowired
	CourseController courseController;
	
	@Test
	@Sql("/courses.sql")
	public void shouldFindAll() {
		Page<CourseDTO> result = courseController.findAll(0, 5, true);
		assertEquals(result.getContent().size(), 5);
	}
	
	

	@Test
	@Sql("/courses.sql")
	public void shouldFindFirstPageThreeCourses() {
		Page<CourseDTO> result = courseController.findAll(0, null, true);
		assertEquals(result.getContent().size(), 3);
	}

	
	@Test
	@Sql("/courses.sql")
	public void shouldFindTwoPageLastPageTwoCourses() {
		Page<CourseDTO> result = courseController.findAll(0, null, true);
		result = courseController.findAll(result.nextPageable().getPageNumber(), 
										  null, true);
		assertEquals(result.getContent().size(), 3);//3 is the defect value.
	}
	
	
	@Test
	@Sql("/coursesFalse.sql")
	public void shouldNotFindCourses() {
		Page<CourseDTO> result = courseController.findAll(0, null, true);
		assertEquals(result.getContent().size(), 0);
	}
	
	
	@Test
	@Sql("/courses.sql")
	public void shouldFindThreeElements1() {
		Page<CourseDTO> result = courseController.findAll(null, null, true);
		assertEquals(result.getContent().size(),3);
	}
	
	@Test
	@Sql("/courses.sql")
	public void shouldFindThreeElements2() {
		Page<CourseDTO> result = courseController.findAll(0, null, true);
		assertEquals(result.getContent().size(),3);
	}	
	
	
	@Test
	@Sql("/courses.sql")
	public void shouldFindThreeElements3() {
		Page<CourseDTO> result = courseController.findAll(null, 1, true);
		assertEquals(result.getContent().size(),3);
	}
}
