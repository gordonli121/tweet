package com.twitter.tweet.service;

import com.twitter.tweet.model.Tweet;

import java.util.List;

public interface TweetService {
    List<Tweet> findAll();
    Tweet findById(String id);
    Tweet postTweet(Tweet tweet);
//    Tweet updateTweet(String id, Tweet tweet);
    void deleteById(String id);
}
