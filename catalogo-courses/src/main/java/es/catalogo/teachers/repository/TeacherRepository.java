package es.catalogo.teachers.repository;

import org.springframework.data.repository.CrudRepository;

import es.catalogo.teachers.entity.Teacher;

public interface TeacherRepository extends CrudRepository<Teacher, Integer>{

}
