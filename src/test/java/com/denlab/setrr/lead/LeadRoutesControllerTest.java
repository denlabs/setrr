package com.denlab.setrr.lead;

import com.denlab.setrr.SetrrApplication;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = SetrrApplication.class)
@AutoConfigureMockMvc
class LeadRoutesControllerTest {

    @Autowired
    private MockMvc mockMvc;


    private ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        mapper.registerModule(new JavaTimeModule());
    }

    @Test
    void shouldGetLead() throws Exception {
        mockMvc.perform(get("/leads/test"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.setterName").value("test"));
//                .andExpect(jsonPath("$.status").value("CREATED"));

    }

    @Test
    void shouldPostLead() throws Exception {
        LeadRouteCreateCmd cmd = new LeadRouteCreateCmd("denlab", "12a", "22A", "Rock Steady", "black/blue/orange");
        mockMvc.perform(post("/leads/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(cmd)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.setterName").value("denlab"));
    }
}

