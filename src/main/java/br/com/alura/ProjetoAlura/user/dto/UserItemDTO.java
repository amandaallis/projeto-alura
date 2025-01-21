package br.com.alura.ProjetoAlura.user.dto;

import br.com.alura.ProjetoAlura.user.Role;
import br.com.alura.ProjetoAlura.user.entity.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class UserItemDTO implements Serializable {

    private final Long id;
    private final String name;
    private final String email;
    private final Role role;


    public UserItemDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.role = user.getRole();
    }
}
