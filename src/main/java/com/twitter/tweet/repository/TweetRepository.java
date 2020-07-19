package com.twitter.tweet.repository;

import com.twitter.tweet.model.Tweet;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

//@Repository
public interface TweetRepository extends MongoRepository<Tweet, String> {

}
