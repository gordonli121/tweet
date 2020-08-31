package com.twitter.tweet.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.twitter.tweet.message.TwitterNotification;

public class JsonUtils {
    public static ObjectMapper objectMapper = new ObjectMapper();
    public static String convertTwitterNotificationMessage(TwitterNotification tn) throws JsonProcessingException {
        return objectMapper.writeValueAsString(tn);
    }
}
