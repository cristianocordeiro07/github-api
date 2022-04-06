package com.cristiano.githubconnect.service.impl;

import com.cristiano.githubconnect.service.GitHubService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GitHubServiceImpl implements GitHubService {

    @Value("${github-url-base}")
    String GITHUB_URL_BASE;

    @Value("${github-token:#{null}}")
    String GITHUB_TOKEN;

    public ResponseEntity<Object> getUsers(Long since) {
        return callGitHubApi(GITHUB_URL_BASE + "?since=" + since);
    }

    public ResponseEntity<Object> getUserDetails(String user) {
        return callGitHubApi(GITHUB_URL_BASE + "/" + user);
    }

    public ResponseEntity<Object> getUserRepos(String user) {
        return callGitHubApi(GITHUB_URL_BASE + "/" + user + "/repos");
    }

    private ResponseEntity<Object> callGitHubApi(String url) {
        HttpHeaders headers = new HttpHeaders();

        //User for private requests
        if (GITHUB_TOKEN != null && !GITHUB_TOKEN.equals("")) {
            headers.add("Authorization", "token " + GITHUB_TOKEN);
        }

        HttpEntity<String> request = new HttpEntity<>(headers);
        RestTemplate template = new RestTemplate();
        return template.exchange(url, HttpMethod.GET, request, Object.class);
    }
}
