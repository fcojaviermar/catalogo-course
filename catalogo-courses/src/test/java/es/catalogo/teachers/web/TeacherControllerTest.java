package es.catalogo.teachers.web;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import es.catalogo.teachers.service.TeacherService;
import es.catalogo.teachers.web.dto.TeacherDTO;

@RunWith(MockitoJUnitRunner.class)
public class TeacherControllerTest {

	@InjectMocks
	TeacherController teacherControler;
	
	@Mock
	TeacherService teacherService;
	
	@Test
	public void shouldCreateTeacher() {
		TeacherDTO input = new TeacherDTO(1, "Pedro");
		ResponseEntity<TeacherDTO> expectedTeacher = new ResponseEntity<TeacherDTO>(new TeacherDTO(2, "Javier"),
																					HttpStatus.OK);
		
		when(teacherService.add(input)).thenReturn(expectedTeacher);
		
		ResponseEntity<TeacherDTO> result = teacherControler.add(input);
		
		assertEquals(expectedTeacher, result);
	}
}
