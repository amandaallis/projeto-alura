package br.com.alura.ProjetoAlura.course.entity;

import br.com.alura.ProjetoAlura.course.Status;
import br.com.alura.ProjetoAlura.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Entity
@Table(name = "Course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "instructorId", referencedColumnName = "id")
    private User instructor;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "inactivateDate")
    private LocalDateTime inactivateDate;

    public Course(LocalDateTime inactivateDate, Status status, String description, User instructor, String code, String name) {
        this.inactivateDate = inactivateDate;
        this.status = status;
        this.description = description;
        this.instructor = instructor;
        this.code = code;
        this.name = name;
    }

    public Course() {
    }
}
