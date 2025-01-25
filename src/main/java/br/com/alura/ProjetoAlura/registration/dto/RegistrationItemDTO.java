package br.com.alura.ProjetoAlura.registration.dto;

import br.com.alura.ProjetoAlura.course.entity.Course;
import br.com.alura.ProjetoAlura.registration.entity.Registration;

import java.io.Serializable;
import java.time.LocalDateTime;

public class RegistrationItemDTO implements Serializable {
    public Long id;
    public LocalDateTime registrationDate;
    public String courseCode;

    public RegistrationItemDTO(Registration registration) {
        this.id = registration.getId();
        this.registrationDate = registration.getRegistrationDate();
        this.courseCode = registration.getCourseId().getCode();
    }
}
