package br.com.alura.ProjetoAlura.course.service;

import br.com.alura.ProjetoAlura.course.Status;
import br.com.alura.ProjetoAlura.course.dto.CourseItemDTO;
import br.com.alura.ProjetoAlura.course.dto.NewCourseDTO;
import br.com.alura.ProjetoAlura.course.entity.Course;
import br.com.alura.ProjetoAlura.course.error.CourseError;
import br.com.alura.ProjetoAlura.course.repository.CourseRepository;
import br.com.alura.ProjetoAlura.infra.security.TokenService;
import br.com.alura.ProjetoAlura.user.entity.User;
import br.com.alura.ProjetoAlura.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class CourseService {
    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;

    @Autowired
    private CourseRepository courseRepository;

    private static final Pattern COURSE_CODE_PATTERN = Pattern.compile("^[a-z]+(-[a-z]+)*$");

    public ResponseEntity<Object> newCourse(NewCourseDTO newCourseDTO, String token) {
        Long userId = tokenService.getUserIdFromToken(token);
        Optional<User> user  = userService.findUserById(userId);

        if(user.isEmpty()) {
            return CourseError.userNotVinculatedWithCourseError();
        }

        ResponseEntity<Object> validationResponse = validateAllParamsWhenCreate(newCourseDTO.getCode());
        if (validationResponse != null) {
            return validationResponse;
        }

        var course = newCourseDTO.toModel(Status.ACTIVE, user.get(), null);
        var itemSaved = courseRepository.save(course);
        var courseItem = new CourseItemDTO(itemSaved);
        return ResponseEntity.status(HttpStatus.CREATED).body(courseItem);
    }


    private ResponseEntity<Object> validateAllParamsWhenCreate(String code) {
        if (!COURSE_CODE_PATTERN.matcher(code).matches()) {
            return CourseError.invalidCodeError();
        }
        var thisCodeIsAlreadyRegistered = courseRepository.existsByCode(code);

        if (thisCodeIsAlreadyRegistered) {
            return CourseError.alreadyExistRegisterCodeError();
        }

        return null;
    }

    @Transactional
    public ResponseEntity<Object> inactivateCourse(String code) {

        var existCode = courseRepository.existsByCode(code);
        if(!existCode) return CourseError.codeNotFoundError();

        courseRepository.updateInactivateData(code);
        return ResponseEntity.ok().build();
    }

    public Optional<Course> findCourseByCodeAndActive(String code) {
        return courseRepository.findByCodeAndInactivateDateIsNull(code);
    }
}
