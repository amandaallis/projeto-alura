package br.com.alura.ProjetoAlura.authorization.controller;

import br.com.alura.ProjetoAlura.authorization.dto.AuthenticationDTO;
import br.com.alura.ProjetoAlura.authorization.service.AuthorizationLoginService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthorizationController {

    @Autowired
    private AuthorizationLoginService authorizationLoginService;

    @PostMapping("/login")
    public ResponseEntity<Object> login (@RequestBody @Valid AuthenticationDTO authenticationDTO) {
      return authorizationLoginService.login(authenticationDTO);
    }
}
