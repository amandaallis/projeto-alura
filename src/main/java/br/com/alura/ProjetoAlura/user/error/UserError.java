package br.com.alura.ProjetoAlura.user.error;

import br.com.alura.ProjetoAlura.util.ErrorItemDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class UserError extends RuntimeException {
    public UserError(String message) {
        super(message);
    }

    public static ResponseEntity<Object> toEmailAlreadyRegisteredError() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorItemDTO("email", "Email já cadastrado no sistema"));

    }

}
