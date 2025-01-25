package br.com.alura.ProjetoAlura.registration.error;

import br.com.alura.ProjetoAlura.util.ErrorItemDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class RegistrationError extends RuntimeException{
    public RegistrationError(String message) {
        super(message);
    }

    public static ResponseEntity<Object> userOrCourseNotFoundToRegistrateError() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorItemDTO("Curso", "Curso não encontrado."));

    }

    public static ResponseEntity<Object> courseAlreadyMatriculated() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorItemDTO("Curso", "Você já está matriculado nesse curso"));

    }
}
