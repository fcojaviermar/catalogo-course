package es.catalogo.teachers.web;

import org.springframework.http.ResponseEntity;

import es.catalogo.teachers.service.TeacherService;
import es.catalogo.teachers.web.dto.TeacherDTO;

public class TeacherController {
	
	private final TeacherService teacherService;
	
	public TeacherController(TeacherService teacherService) {
		super();
		this.teacherService = teacherService;
	}



	public ResponseEntity<TeacherDTO> add(TeacherDTO teacher) {
		return teacherService.add(teacher);
	}
}
