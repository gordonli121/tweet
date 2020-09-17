package com.twitter.tweet.service;

import com.twitter.tweet.exception.PersistenceException;
import com.twitter.tweet.model.Tweet;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import java.util.List;

public interface TweetService {
    List<Tweet> findAll(Pageable request) throws Exception;
    Tweet findById(String id) throws Exception;
    Tweet createTweet(Tweet tweet) throws Exception;

    Tweet updateTweet(String id, String content) throws Exception;
    Tweet deleteById(String id) throws Exception;
}
