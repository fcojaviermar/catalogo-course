package es.catalogo.teachers.web.dto;

import es.catalogo.teachers.entity.Teacher;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class TeacherDTO {

	private int id;
	private String name;
	
	
	public TeacherDTO(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	
	public TeacherDTO(Teacher teacher) {
		this.id = teacher.getId();
		this.name = teacher.getName();
	}

	
}
