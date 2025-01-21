package br.com.alura.ProjetoAlura.user.entity;

import br.com.alura.ProjetoAlura.user.Role;
import br.com.alura.ProjetoAlura.util.EncryptUtil;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
@AllArgsConstructor
@Getter

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "createdAt")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "name")
    private String name;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    public User(String name, String email, Role role, String password) {
        this.name = name;
        this.role = role;
        this.email = email;
        this.password = EncryptUtil.toMD5(password);
    }
}