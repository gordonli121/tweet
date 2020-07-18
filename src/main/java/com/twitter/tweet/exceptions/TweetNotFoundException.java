package com.twitter.tweet.exceptions;

import org.bson.types.ObjectId;

public class TweetNotFoundException extends RuntimeException{
    public TweetNotFoundException(ObjectId id) {
        super("Could not find tweet " + id);
    }
}
