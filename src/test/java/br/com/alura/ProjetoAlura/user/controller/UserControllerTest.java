package br.com.alura.ProjetoAlura.user.controller;

import br.com.alura.ProjetoAlura.user.dto.NewStudentUserDTO;
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

    @Test
    void newStudent__should_return_bad_request_when_password_is_blank() throws Exception {
        NewStudentUserDTO newStudentUserDTO = new NewStudentUserDTO();
        newStudentUserDTO.setEmail("test@test.com");
        newStudentUserDTO.setName("Charles");
        newStudentUserDTO.setPassword("");

        mockMvc.perform(post("/user/newStudent")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newStudentUserDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$[0].field").value("password"))
                .andExpect(jsonPath("$[0].message").isNotEmpty());
    }

    @Test
    void newStudent__should_return_bad_request_when_email_is_blank() throws Exception {
        NewStudentUserDTO newStudentUserDTO = new NewStudentUserDTO();
        newStudentUserDTO.setEmail("");
        newStudentUserDTO.setName("Charles");
        newStudentUserDTO.setPassword("mudar123");

        mockMvc.perform(post("/user/newStudent")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newStudentUserDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$[0].field").value("email"))
                .andExpect(jsonPath("$[0].message").isNotEmpty());
    }

    @Test
    void newStudent__should_return_bad_request_when_email_is_invalid() throws Exception {
        NewStudentUserDTO newStudentUserDTO = new NewStudentUserDTO();
        newStudentUserDTO.setEmail("Charles");
        newStudentUserDTO.setName("Charles");
        newStudentUserDTO.setPassword("mudar123");

        mockMvc.perform(post("/user/newStudent")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newStudentUserDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$[0].field").value("email"))
                .andExpect(jsonPath("$[0].message").isNotEmpty());
    }
}