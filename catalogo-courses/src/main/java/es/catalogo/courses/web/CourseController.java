package es.catalogo.courses.web;

import javax.ws.rs.QueryParam;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import es.catalogo.courses.service.CourseService;
import es.catalogo.courses.web.dto.CourseDTO;

@RestController //Replace @Controller and @ResponseBody
public class CourseController {

	private final CourseService courseService;
		
	public CourseController(CourseService courseService) {
		super();
		this.courseService = courseService;
	}

	//courses -> sustantivo, plural 
	@RequestMapping(value = "/courses", method = RequestMethod.POST)
	public ResponseEntity<CourseDTO> add(@RequestBody CourseDTO course) {
		return courseService.add(course);
	}


	@RequestMapping(value = "/courses", method=RequestMethod.GET)
	public ResponseEntity<Page<CourseDTO>> findAll(@QueryParam("page")Integer page, @QueryParam("size")Integer size, 
								   @QueryParam("active") Boolean active) {
		return courseService.findAll(page, size, active);
	}
}
