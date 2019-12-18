package es.catalogo.courses.web.dto;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import es.catalogo.courses.entity.Course;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter 
@EqualsAndHashCode
public class CourseDTO implements Serializable {

	private static final long serialVersionUID = -3004363643555780755L;

	
	private boolean active;
	
	@Min(value = 1)
	private int idTeacher;
	
	@NotEmpty
	private String title;
	
	@Min(value = 1)
	private int hours;
	
	@Min(value = 1)
	@Max(value = 3)
	private int level;

	
	public CourseDTO() {
	}
	
	
	public CourseDTO(boolean active, int idTeacher, String title, int hours, int level) {
		super();
		this.active = active;
		this.idTeacher = idTeacher;
		this.title = title;
		this.hours = hours;
		this.level = level;
	}


	public CourseDTO(Course course) {
		this.active = course.isActive();
		this.idTeacher = course.getIdTeacher();
		this.title = course.getTitle();
		this.hours = course.getHours();
		this.level = course.getLevel();
	}
	
}
