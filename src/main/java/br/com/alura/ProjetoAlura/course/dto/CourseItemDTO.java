package br.com.alura.ProjetoAlura.course.dto;

import br.com.alura.ProjetoAlura.course.entity.Course;
import java.io.Serializable;

public class CourseItemDTO implements Serializable {
    public Long id;
    public String code;
    public String description;
    public String name;


    public CourseItemDTO(Course course) {
        this.id = course.getId();
        this.code = course.getCode();
        this.description = course.getDescription();
        this.name = course.getName();
    }
}
