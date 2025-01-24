package br.com.alura.ProjetoAlura.authorization.service;

import br.com.alura.ProjetoAlura.authorization.dto.AuthenticationDTO;
import br.com.alura.ProjetoAlura.authorization.dto.LoginResponseDTO;
import br.com.alura.ProjetoAlura.infra.security.TokenService;
import br.com.alura.ProjetoAlura.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationLoginService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    public ResponseEntity<Object> login(AuthenticationDTO authenticationDTO) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(
                authenticationDTO.email(), authenticationDTO.password()
        );
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((User) auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }
}
