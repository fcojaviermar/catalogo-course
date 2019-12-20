package es.catalogo.teachers.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import es.catalogo.teachers.entity.Teacher;
import es.catalogo.teachers.repository.TeacherRepository;
import es.catalogo.teachers.service.TeacherService;
import es.catalogo.teachers.web.dto.TeacherDTO;

@Service
public class TeacherServiceImpl implements TeacherService {

	private final TeacherRepository teacherRepository;
	
	//Dependency injection with constructor.
	public TeacherServiceImpl(TeacherRepository teacherRepository) {
		super();
		this.teacherRepository = teacherRepository;
	}



	public ResponseEntity<TeacherDTO> add(TeacherDTO teacherDTO) {
		Teacher teacher = new Teacher(teacherDTO);

		teacher = teacherRepository.save(teacher);
		return new ResponseEntity<>(new TeacherDTO(teacher), HttpStatus.OK);
	}

}
