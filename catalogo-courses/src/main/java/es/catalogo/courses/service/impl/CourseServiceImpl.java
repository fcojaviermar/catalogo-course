package es.catalogo.courses.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import es.catalogo.courses.entity.Course;
import es.catalogo.courses.exception.NoContentException;
import es.catalogo.courses.repository.CourseRepository;
import es.catalogo.courses.service.CourseService;
import es.catalogo.courses.web.dto.CourseDTO;



@Service
public class CourseServiceImpl implements CourseService {

	private final int page;
	private final int size;
	
	private final CourseRepository courseRepository;
	
	public CourseServiceImpl(CourseRepository courseRepository,
							 @Value("${page}")Integer page, @Value("${size}")Integer size) {
		super();
		this.courseRepository = courseRepository; 
		this.page = page;
		this.size = size;
	}



	public ResponseEntity<CourseDTO> add(CourseDTO courseDto) {
		Course course = courseRepository.save(new Course(courseDto));
		return new ResponseEntity<CourseDTO>(new CourseDTO(course), HttpStatus.OK);
	}

	
	
	public ResponseEntity<Page<CourseDTO>> findAll(Integer page, Integer size, Boolean active) throws NoContentException {
		List<CourseDTO> result = null;
		Page<Course> listCourses = null;
		
		Pageable pageRequest = page(page, size);
		
		if (null != active) {
			listCourses = courseRepository.findAllByActive(pageRequest, active);
		} else {
			listCourses = courseRepository.findAll(pageRequest);
		}
	
		
		if (listCourses.isEmpty()) {
			throw new NoContentException("There is no courses.");
		
		} else {
			result = listCourses.stream().map(course -> new CourseDTO(course)).collect(Collectors.toList());
			return new ResponseEntity<Page<CourseDTO>>(new PageImpl<CourseDTO>(result, listCourses.getPageable(), 
																			   listCourses.getTotalElements()),
													   HttpStatus.OK);
		}
	}
	
	
	private Pageable page(Integer page, Integer size) {
		if ( (null == page) || (null == size) ) {
			return  PageRequest.of(this.page, this.size);
		}
		return  PageRequest.of(page, size);
	}
}
