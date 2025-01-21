package br.com.alura.ProjetoAlura.user.dto;

import br.com.alura.ProjetoAlura.user.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import static br.com.alura.ProjetoAlura.user.Role.STUDENT;

@Getter
@Setter
public class NewStudentUserDTO {

    @NotNull
    @Length(min = 3, max = 50)
    private String name;

    @NotBlank
    @Email
    private String email;

    @NotNull
    @Length(min = 8, max = 16)
    private String password;

    public User toModel() {
        return new User(name, email, STUDENT, password);
    }

}
