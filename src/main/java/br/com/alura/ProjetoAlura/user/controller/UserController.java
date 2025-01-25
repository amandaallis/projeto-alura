package br.com.alura.ProjetoAlura.user.controller;

import br.com.alura.ProjetoAlura.user.dto.UserItemDTO;
import br.com.alura.ProjetoAlura.user.dto.NewUserDTO;
import br.com.alura.ProjetoAlura.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Transactional
    @PostMapping("/user/newUser")
    public ResponseEntity<Object> newStudent(@RequestBody @Valid NewUserDTO newStudent) {
       return userService.newStudent(newStudent);
    }

    @GetMapping("/user/all")
    public List<UserItemDTO> listAllUsers() {
        return userService.listAllUsers();
    }

}
