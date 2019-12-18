package es.catalogo.courses.service;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import es.catalogo.courses.web.dto.CourseDTO;

public interface CourseService {

	public ResponseEntity<CourseDTO> add(CourseDTO courseDto);

	public ResponseEntity<Page<CourseDTO>> findAll(Integer page, Integer size, Boolean active);
}
