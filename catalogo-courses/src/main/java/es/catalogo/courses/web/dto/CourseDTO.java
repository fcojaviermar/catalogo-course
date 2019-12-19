package es.catalogo.courses.web.dto;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import es.catalogo.courses.entity.Course;
import es.catalogo.courses.enums.Level;
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
	private int teacher;
	
	@NotEmpty
	private String title;
	
	@Min(value = 1)
	private int hours;
	
	private Level level;

	
	public CourseDTO() {
	}
	
	
	public CourseDTO(boolean active, int teacher, String title, int hours, Level level) {
		super();
		this.active = active;
		this.teacher = teacher;
		this.title = title;
		this.hours = hours;
		this.level = level;
	}


	public CourseDTO(Course course) {
		this.active = course.isActive();
		this.teacher = course.getTeacher();
		this.title = course.getTitle();
		this.hours = course.getHours();
		this.level = course.getLevel();
	}
	
}
