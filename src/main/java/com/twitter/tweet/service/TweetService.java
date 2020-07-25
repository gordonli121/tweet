package com.twitter.tweet.service;

import com.twitter.tweet.model.Tweet;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TweetService {
    ResponseEntity<List<Tweet>> findAll();
    ResponseEntity<Tweet> findById(String id);
    ResponseEntity<Tweet> postTweet(Tweet tweet);

    ResponseEntity<Tweet> updateTweet(String id, String content);
    ResponseEntity<HttpStatus> deleteById(String id);
}
