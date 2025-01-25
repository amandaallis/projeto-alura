package br.com.alura.ProjetoAlura.user.service;

import br.com.alura.ProjetoAlura.user.Role;
import br.com.alura.ProjetoAlura.user.dto.NewUserDTO;
import br.com.alura.ProjetoAlura.user.entity.User;
import br.com.alura.ProjetoAlura.user.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserServiceTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private NewUserDTO validNewStudentDTO;

    @BeforeEach
    void setUp() {
        validNewStudentDTO = new NewUserDTO();
        validNewStudentDTO.setEmail("charles@alura.com.br");
        validNewStudentDTO.setName("Charles");
        validNewStudentDTO.setPassword("mudar123");
    }

    @Test
    void newStudent__should_return_bad_request_when_email_already_exists() throws Exception {
        when(userRepository.existsByEmail(validNewStudentDTO.getEmail())).thenReturn(true);

        mockMvc.perform(post("/user/newUser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(validNewStudentDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.field").value("email"))
                .andExpect(jsonPath("$.message").value("Email já cadastrado no sistema"));
    }

    @Test
    void newStudent__should_return_created_when_user_request_is_valid() throws Exception {
        when(userRepository.existsByEmail(validNewStudentDTO.getEmail())).thenReturn(false);
        when(userRepository.save(any(User.class))).thenReturn(validNewStudentDTO.toModel());

        mockMvc.perform(post("/user/newUser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(validNewStudentDTO)))
                .andExpect(status().isCreated());
    }

    @Test
    void listAllUsers__should_list_all_users() throws Exception {
        User user1 = new User("User 1", "user1@test.com", Role.STUDENT,"mudar123");
        User user2 = new User("User 2", "user2@test.com",Role.STUDENT,"mudar123");

        when(userRepository.findAll()).thenReturn(Arrays.asList(user1, user2));

        mockMvc.perform(get("/user/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("User 1"))
                .andExpect(jsonPath("$[1].name").value("User 2"));
    }
}
