package com.twitter.tweet.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.twitter.tweet.message.TwitterNotification;
import org.springframework.beans.factory.annotation.Value;

public interface TweetMessageService {

    public void sendMessage(TwitterNotification msg) throws JsonProcessingException;
}
