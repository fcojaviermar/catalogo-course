package es.catalogo.teachers.service;

import org.springframework.http.ResponseEntity;

import es.catalogo.teachers.web.dto.TeacherDTO;

public interface TeacherService {

	public ResponseEntity<TeacherDTO> add(TeacherDTO teacherDTO);
	
}
