package com.twitter.tweet.service;

import com.twitter.tweet.exceptions.TweetNotFoundException;
import com.twitter.tweet.model.Tweet;
import com.twitter.tweet.repository.TweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TweetServiceImp implements TweetService{
    @Autowired
    private TweetRepository tweetRepository;

    @Override
    public List<Tweet> findAll() {
        return tweetRepository.findAll();
    }

    @Override
    public Tweet findById(String id) {
        return tweetRepository.findById(id)
                .orElseThrow(() -> new TweetNotFoundException(id));
    }

    @Override
    public Tweet postTweet(Tweet tweet) {
        return tweetRepository.save(tweet);
    }

//    @Override
//    public Tweet updateTweet(String id, Tweet tweet) {
//        Optional<Tweet> model = tweetRepository.findById(id);
//
//        return null;
//    }

    @Override
    public void deleteById(String id) {
        tweetRepository.deleteById(id);
    }
}
