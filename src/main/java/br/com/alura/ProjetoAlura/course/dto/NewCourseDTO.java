package br.com.alura.ProjetoAlura.course.dto;

import br.com.alura.ProjetoAlura.course.Status;
import br.com.alura.ProjetoAlura.course.entity.Course;
import br.com.alura.ProjetoAlura.user.entity.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

@Getter
@Setter
public class NewCourseDTO {

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    @Length(min = 4, max = 10)
    private String code;

    private String description;

    public Course toModel(Status status, User user, LocalDateTime inactivateDate){
        return new Course(inactivateDate, status, description, user, code, name);
    }
}
