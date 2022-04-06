package com.cristiano.githubconnect.service;

import org.springframework.http.ResponseEntity;

public interface GitHubService {

    ResponseEntity<Object> getUsers(Long since);

    ResponseEntity<Object> getUserDetails(String user);

    ResponseEntity<Object> getUserRepos(String user);
}
