package br.com.alura.ProjetoAlura.registration.service;

import br.com.alura.ProjetoAlura.course.entity.Course;
import br.com.alura.ProjetoAlura.course.service.CourseService;
import br.com.alura.ProjetoAlura.infra.security.TokenService;
import br.com.alura.ProjetoAlura.registration.dto.NewRegistrationDTO;
import br.com.alura.ProjetoAlura.registration.dto.RegistrationItemDTO;
import br.com.alura.ProjetoAlura.registration.dto.RegistrationReportDTO;
import br.com.alura.ProjetoAlura.registration.error.RegistrationError;
import br.com.alura.ProjetoAlura.registration.repository.RegistrationRepository;
import br.com.alura.ProjetoAlura.user.entity.User;
import br.com.alura.ProjetoAlura.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegistrationService {
    @Autowired
    private TokenService tokenService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private UserService userService;

    @Autowired
    private RegistrationRepository registrationRepository;

    public ResponseEntity<Object> newRegistration(NewRegistrationDTO newRegistration, String token) {
        Long userId = tokenService.getUserIdFromToken(token);
        Optional<User> user  = userService.findUserById(userId);

        Optional<Course> course = courseService.findCourseByCodeAndActive(newRegistration.getCourseCode());

        if(user.isEmpty() || course.isEmpty()) {
            return RegistrationError.userOrCourseNotFoundToRegistrateError();
        }

        var existRegister = registrationRepository.findByCourseIdAndUserId(course.get().getId(), userId);
        if(!existRegister.isEmpty()) {
            return RegistrationError.courseAlreadyMatriculated();
        }

        var newRegister = newRegistration.toModel(course.get(), user.get());
        var registerSaved = registrationRepository.save(newRegister);
        var item = new RegistrationItemDTO(registerSaved);

        return ResponseEntity.status(HttpStatus.CREATED).body(item);

    }

    public List<RegistrationReportDTO> findRanking() {
        return registrationRepository.findRankingCoursesRegistered();
    }
}
