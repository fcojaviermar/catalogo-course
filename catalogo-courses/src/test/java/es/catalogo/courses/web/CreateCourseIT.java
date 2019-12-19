package es.catalogo.courses.web;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import es.catalogo.courses.CatalogueCoursesApplication;
import es.catalogo.courses.enums.Level;
import es.catalogo.courses.web.dto.CourseDTO;

@RunWith(SpringRunner.class)
@SpringBootTest (classes = CatalogueCoursesApplication.class)
public class CreateCourseIT {

	@Autowired
	private CourseController courseController;
	
	
	@Test
	public void createCourse() {
		CourseDTO input = new CourseDTO(false, 1, "Micro", 40, Level.HIGH);
		
		ResponseEntity<CourseDTO> result = courseController.add(input);
		
		assertNotNull(result.getBody());
	}

}
