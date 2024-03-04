package com.dion.stekkieoverflow.controller;

import com.dion.stekkieoverflow.dto.AnswerDTO;
import com.dion.stekkieoverflow.dto.PlantDTO;
import com.dion.stekkieoverflow.dto.QuestionDTO;
import com.dion.stekkieoverflow.service.AnswerService;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.containsString;

@WebMvcTest(AnswerController.class)
class AnswerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AnswerService answerService;

    // Mock is necessary because the UserDetailsService bean in the SecurityConfiguration class conflicts with
    // the spring security test framework
    @MockBean
    private UserDetailsService userDetailsService;

    @Test
    @WithMockUser(username = "user", password = "password", roles = {"USER"})
    void testGetAnswersAsAUser() throws Exception {
        PlantDTO plant = new PlantDTO(1l, "", "");
        QuestionDTO questionDTO = new QuestionDTO(1l, "", "", "", plant);
        AnswerDTO answerDTO = new AnswerDTO(1l, "answertext", "userId", questionDTO);
        Mockito.when(answerService.getAllAnswers()).thenReturn(Lists.newArrayList(answerDTO));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/answers"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(containsString("answertext")));
    }

    @Test
    @WithAnonymousUser
    void testIsUnAuthorized() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/answers"))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

}
