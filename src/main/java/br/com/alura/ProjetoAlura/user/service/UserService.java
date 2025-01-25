package br.com.alura.ProjetoAlura.user.service;

import br.com.alura.ProjetoAlura.user.dto.NewUserDTO;
import br.com.alura.ProjetoAlura.user.dto.UserItemDTO;
import br.com.alura.ProjetoAlura.user.entity.User;
import static br.com.alura.ProjetoAlura.user.error.UserError.toEmailAlreadyRegisteredError;
import br.com.alura.ProjetoAlura.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseEntity<Object> newStudent(NewUserDTO newStudent) {
        boolean alreadyExistUserWithEmail = userRepository.existsByEmail(newStudent.getEmail());

        if (alreadyExistUserWithEmail) {
            return toEmailAlreadyRegisteredError();
        }

        User user = newStudent.toModel();
        User userSaved = userRepository.save(user);
        UserItemDTO userResponseDTO = new UserItemDTO(userSaved);

        return ResponseEntity.status(HttpStatus.CREATED).body(userResponseDTO);
    }

    public List<UserItemDTO> listAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserItemDTO::new)
                .toList();
    }

    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }
}

