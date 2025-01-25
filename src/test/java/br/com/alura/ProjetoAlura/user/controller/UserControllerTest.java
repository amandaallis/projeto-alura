package br.com.alura.ProjetoAlura.user.controller;

import br.com.alura.ProjetoAlura.infra.security.TokenService;
import br.com.alura.ProjetoAlura.user.dto.NewUserDTO;
import br.com.alura.ProjetoAlura.user.repository.UserRepository;
import br.com.alura.ProjetoAlura.user.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private TokenService tokenService;


    @Test
    void newStudent__should_return_bad_request_when_password_is_blank() throws Exception {
        NewUserDTO newUserDTO = new NewUserDTO();
        newUserDTO.setEmail("test@test.com");
        newUserDTO.setName("Charles");
        newUserDTO.setPassword("");

        mockMvc.perform(post("/user/newUser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newUserDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$[0].field").value("password"))
                .andExpect(jsonPath("$[0].message").isNotEmpty());
    }

    @Test
    void newStudent__should_return_bad_request_when_email_is_blank() throws Exception {
        NewUserDTO newUserDTO = new NewUserDTO();
        newUserDTO.setEmail("");
        newUserDTO.setName("Charles");
        newUserDTO.setPassword("mudar123");

        mockMvc.perform(post("/user/newUser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newUserDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$[0].field").value("email"))
                .andExpect(jsonPath("$[0].message").isNotEmpty());
    }

    @Test
    void newStudent__should_return_bad_request_when_email_is_invalid() throws Exception {
        NewUserDTO newUserDTO = new NewUserDTO();
        newUserDTO.setEmail("Charles");
        newUserDTO.setName("Charles");
        newUserDTO.setPassword("mudar123");

        mockMvc.perform(post("/user/newUser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newUserDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$[0].field").value("email"))
                .andExpect(jsonPath("$[0].message").isNotEmpty());
    }
}