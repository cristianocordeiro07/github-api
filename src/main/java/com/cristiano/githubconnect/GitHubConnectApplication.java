package com.cristiano.githubconnect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class GitHubConnectApplication {
	public static void main(String[] args) {
		SpringApplication.run(GitHubConnectApplication.class, args);
	}
}
