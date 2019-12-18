package es.catalogo.courses.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import es.catalogo.courses.web.dto.CourseDTO;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter 
@Getter
@EqualsAndHashCode
@Entity
public class Course {
	
	@Id
	private int id;
	private boolean active;
	private int idTeacher;
	private String title;
	private int hours;
	private int level;
	
	public Course() {
	}
	
	public Course(int id, boolean active, int idTeacher, String title, int hours, int level) {
		super();
		this.id = id;
		this.active = active;
		this.idTeacher = idTeacher;
		this.title = title;
		this.hours = hours;
		this.level = level;
	}


	public Course(CourseDTO courseDTO) {
		this.active = courseDTO.isActive();
		this.idTeacher = courseDTO.getIdTeacher();
		this.title = courseDTO.getTitle();
		this.hours = courseDTO.getHours();
		this.level = courseDTO.getLevel();
	}

}
