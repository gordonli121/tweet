package com.twitter.tweet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableMongoRepositories
public class TweetApplication {
	public static void main(String[] args) {
		SpringApplication.run(TweetApplication.class, args);
	}
}
