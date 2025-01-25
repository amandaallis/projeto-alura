package br.com.alura.ProjetoAlura.registration.controller;

import br.com.alura.ProjetoAlura.registration.dto.NewRegistrationDTO;
import br.com.alura.ProjetoAlura.registration.service.RegistrationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @PostMapping("/registration/new")
    public ResponseEntity<Object> createCourse(
            @Valid
            @RequestBody NewRegistrationDTO newRegistration,
            @RequestHeader(value = "Authorization") String authorizationHeader
    ) {
        return registrationService.newRegistration(newRegistration, authorizationHeader);
    }

    @GetMapping("/registration/report")
    public ResponseEntity<Object> report() {
        var item = registrationService.findRanking();
        return ResponseEntity.ok(item);
    }
}
