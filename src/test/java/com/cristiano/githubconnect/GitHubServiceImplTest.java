package com.cristiano.githubconnect;

import com.cristiano.githubconnect.service.GitHubService;
import com.cristiano.githubconnect.service.impl.GitHubServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest
@AutoConfigureMockMvc
class GitHubServiceImplTest {

    private final String userName = "cristianocordeiro07";

    @TestConfiguration
    static class GitHubServiceImplTestContextConfiguration {

        @Bean
        public GitHubService gitHubService() {
            return new GitHubServiceImpl();
        }
    }

    @Autowired
    private GitHubService gitHubService;

    @Test
    public void testGetUsers() {
        ResponseEntity<Object> response = gitHubService.getUsers(10L);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testGetUserDetails() {
        ResponseEntity<Object> response = gitHubService.getUserDetails(userName);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testGetUserRepos() {
        ResponseEntity<Object> response = gitHubService.getUserRepos(userName);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
    }
}