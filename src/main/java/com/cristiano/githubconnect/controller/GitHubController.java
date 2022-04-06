package com.cristiano.githubconnect.controller;

import com.cristiano.githubconnect.service.GitHubService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/api/users")
public class GitHubController {

    private final GitHubService gitHubService;

    public GitHubController(GitHubService gitHubService) {
        this.gitHubService = gitHubService;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @GetMapping()
    public ResponseEntity<Object> getUsers(@RequestParam Long since) {
        try {
            return gitHubService.getUsers(since);
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @GetMapping("/{username}/details")
    public ResponseEntity<Object> getUserDetails(@PathVariable String username) {
        try {
            return gitHubService.getUserDetails(username);
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @GetMapping("/{username}/repos")
    public ResponseEntity<Object> getUserRepos(@PathVariable String username) {
        try {
            return gitHubService.getUserRepos(username);
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}