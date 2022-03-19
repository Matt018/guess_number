package com.example.guess_number;


import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class EndpointsTest {

    @Autowired
    private MockMvc mockMvs;


    @Test
    public void whenGetRequestToStart_thenCorrectResponse() throws Exception {

        mockMvs.perform(MockMvcRequestBuilders.get("/apigame/start")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    public void whenGetGuessRequestToGuess_thenCorrectResponse() throws Exception {

        mockMvs.perform(MockMvcRequestBuilders.get("/apigame/guess")
                        .param("id","9456")
                        .param("shot", "50")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    public void whenGetRequestToHighscores_thenCorrectResponse() throws Exception {

        mockMvs.perform(MockMvcRequestBuilders.get("/apigame/highscores")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
