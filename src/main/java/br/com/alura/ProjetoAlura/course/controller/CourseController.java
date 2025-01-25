package br.com.alura.ProjetoAlura.course.controller;

import br.com.alura.ProjetoAlura.course.dto.NewCourseDTO;
import br.com.alura.ProjetoAlura.course.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping("/course/new")
    public ResponseEntity<Object> createCourse(
            @Valid
            @RequestBody NewCourseDTO newCourse,
            @RequestHeader(value = "Authorization") String authorizationHeader
    ) {
        return courseService.newCourse(newCourse, authorizationHeader);
    }

    @PostMapping("/course/{code}/inactive")
    public ResponseEntity<Object> createCourse(@PathVariable("code") String courseCode) {
        return courseService.inactivateCourse(courseCode);
    }
}
