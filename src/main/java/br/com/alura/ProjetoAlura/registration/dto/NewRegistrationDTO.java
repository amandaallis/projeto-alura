package br.com.alura.ProjetoAlura.registration.dto;

import br.com.alura.ProjetoAlura.course.entity.Course;
import br.com.alura.ProjetoAlura.registration.entity.Registration;
import br.com.alura.ProjetoAlura.user.entity.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class NewRegistrationDTO {

    @NotBlank
    @NotNull
    private String courseCode;

    public NewRegistrationDTO() {}

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public Registration toModel(Course course, User user) {
        return new Registration(LocalDateTime.now(), course, user);
    }
}
