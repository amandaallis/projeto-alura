package br.com.alura.ProjetoAlura.course.error;

import br.com.alura.ProjetoAlura.util.ErrorItemDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class CourseError extends RuntimeException {
    public CourseError(String message) {
        super(message);
    }

    public static ResponseEntity<Object> userNotVinculatedWithCourseError() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorItemDTO("Usuário", "Usuário não encontrado para realizar vinculo com curso"));
    }

    public static ResponseEntity<Object> alreadyExistRegisterCodeError() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorItemDTO("Código", "Cursos não podem ter o mesmo código"));
    }

    public static ResponseEntity<Object> invalidCodeError() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorItemDTO("Código", "\"O código do curso é inválido. Ele deve conter apenas letras minúsculas e hífens, sem espaços ou caracteres especiais.\""));
    }

    public static ResponseEntity<Object> codeNotFoundError() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorItemDTO("Código", "\"Código não encontrado\""));

    }

}