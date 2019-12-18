package es.catalogo.teachers.service;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import es.catalogo.teachers.entity.Teacher;
import es.catalogo.teachers.repository.TeacherRepository;
import es.catalogo.teachers.service.impl.TeacherServiceImpl;
import es.catalogo.teachers.web.dto.TeacherDTO;

@RunWith(MockitoJUnitRunner.class)
public class TeacherServiceTest {
	
	@InjectMocks
	private TeacherServiceImpl teacherService;
	
	@Mock
	private TeacherRepository teacherRepository;
	
	
	@Test
	public void shouldCreateTeacher() {
		TeacherDTO resultExpected = new TeacherDTO(1, "Pedro");
		TeacherDTO inputTeacher = new TeacherDTO(2, "Javier");
		
		when(teacherRepository.save(new Teacher(inputTeacher))).thenReturn(new Teacher(1, "Pedro"));
		ResponseEntity<TeacherDTO> result = teacherService.add(inputTeacher);
		
		assertTrue(result.getBody().equals(resultExpected));
	}
}
