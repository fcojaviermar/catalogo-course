package es.catalogo.courses.service;

import org.springframework.http.ResponseEntity;

import es.catalogo.courses.web.dto.CourseDTO;
import es.catalogo.teachers.web.dto.PageImplResponse;

public interface CourseService {

	public ResponseEntity<CourseDTO> add(CourseDTO courseDto);

	public ResponseEntity<PageImplResponse<CourseDTO>> findAll(Integer page, Integer size, Boolean active);
}
