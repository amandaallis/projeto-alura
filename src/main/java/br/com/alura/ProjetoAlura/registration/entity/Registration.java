package br.com.alura.ProjetoAlura.registration.entity;

import br.com.alura.ProjetoAlura.course.entity.Course;
import br.com.alura.ProjetoAlura.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;
@Getter
@Table(name = "Registration")
@Entity
public class Registration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long id;

    @Column(name = "registrationDate")
    public LocalDateTime registrationDate;

    @ManyToOne
    @JoinColumn(name = "courseId", referencedColumnName = "id")
    public Course courseId;

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id")
    public User userId;

    public Registration(LocalDateTime registrationDate, Course courseId, User userId) {
        this.registrationDate = registrationDate;
        this.courseId = courseId;
        this.userId = userId;
    }

    public Registration() {
    }
}
