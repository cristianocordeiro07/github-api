package com.cristiano.githubconnect;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class GitHubControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @LocalServerPort
    private int port;

    private final String userName = "cristianocordeiro07";

    @Test
    public void testGetUsersExptectedValues() throws Exception {

        String url = createURL() + "?since=10";
        mockMvc.perform(get(url).header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.[0].login").exists())
                .andExpect(jsonPath("$.[0].login").isString())
                .andExpect(jsonPath("$.[0].type").exists())
                .andExpect(jsonPath("$.[0].type").isString());
    }

    @Test
    public void testGetUserDetailsExptectedValues() throws Exception {

        String url = createURL() + "/" + userName + "/details";
        mockMvc.perform(get(url).header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.login").exists())
                .andExpect(jsonPath("$.login").isString())
                .andExpect(jsonPath("$.public_repos").exists())
                .andExpect(jsonPath("$.public_repos").isNumber());
    }

    @Test
    public void testGetUserReposExptectedValues() throws Exception {

        String url = createURL() + "/" + userName + "/repos";
        mockMvc.perform(get(url).header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.[0].id").exists())
                .andExpect(jsonPath("$.[0].id").isNumber())
                .andExpect(jsonPath("$.[0].name").exists())
                .andExpect(jsonPath("$.[0].name").isString())
                .andExpect(jsonPath("$.[0].private").exists())
                .andExpect(jsonPath("$.[0].private").isBoolean());
    }

    private String createURL() {
        String urlRest = "/api/users";
        return "http://localhost:" + port + urlRest;
    }
}