package com.twitter.tweet.service;

import com.twitter.tweet.model.Tweet;
import com.twitter.tweet.repository.TweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TweetServiceImp implements TweetService{
    @Autowired
    private TweetRepository tweetRepository;

    @Override
    public ResponseEntity<List<Tweet>> findAll() {
        try {
            List<Tweet> tweets = tweetRepository.findAll();
            if (tweets.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(tweets, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Tweet> findById(String id) {
        Optional<Tweet> tweetData = tweetRepository.findById(id);

        return tweetData.map(tweet -> new ResponseEntity<>(tweet, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @Override
    public ResponseEntity<Tweet> postTweet(Tweet tweet) {
        try {
            tweetRepository.save(tweet);
            return new ResponseEntity<>(tweet, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @Override
    public ResponseEntity<Tweet> updateTweet(String id, String content) {
        Optional<Tweet> tweetData = tweetRepository.findById(id);

        if (tweetData.isPresent()) {
            Tweet _tweet = tweetData.get();
            _tweet.setContent(content);
            _tweet.setTimestamp(new Date());
            return new ResponseEntity<>(tweetRepository.save(_tweet), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<HttpStatus> deleteById(String id) {
        try {
            tweetRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }
}
