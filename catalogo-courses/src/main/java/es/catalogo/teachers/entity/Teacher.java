package es.catalogo.teachers.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import es.catalogo.teachers.web.dto.TeacherDTO;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@Entity
public class Teacher {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@NotEmpty
	private String name;
	
	public Teacher(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public Teacher(TeacherDTO inputTeacher) {
		this.id = inputTeacher.getId();
		this.name = inputTeacher.getName();
	}

	public Teacher(int id) {
		this.id = id;
	}
}
