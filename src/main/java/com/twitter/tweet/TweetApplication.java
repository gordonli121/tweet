package com.twitter.tweet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class TweetApplication {
	public static void main(String[] args) {
		SpringApplication.run(TweetApplication.class, args);
	}
}
