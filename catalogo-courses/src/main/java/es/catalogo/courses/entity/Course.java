package es.catalogo.courses.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import es.catalogo.courses.enums.Level;
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
	
	private int teacher;
	
	private String title;
	private int hours;
	private Level level;
	
	public Course() {
	}
	
	public Course(int id, boolean active, int idTeacher, String title, int hours, Level level) {
		super();
		this.id = id;
		this.active = active;
		this.teacher = idTeacher;
		this.title = title;
		this.hours = hours;
		this.level = level;
	}


	public Course(CourseDTO courseDTO) {
		this.active = courseDTO.isActive();
		this.teacher = courseDTO.getTeacher();
		this.title = courseDTO.getTitle();
		this.hours = courseDTO.getHours();
		this.level = courseDTO.getLevel();
	}

}
