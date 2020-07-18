package com.twitter.tweet.controller;

import com.twitter.tweet.exceptions.TweetNotFoundException;
import com.twitter.tweet.model.Tweet;
import com.twitter.tweet.repository.TweetRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("tweets")
public class TweetController {
    @Autowired
    private TweetRepository tweetRepository;

    @GetMapping
    public List<Tweet> getTweet(){
        return tweetRepository.findAll();
    }

    @PostMapping
    public Tweet postTweet(@RequestBody Tweet tweet){
        return tweetRepository.save(tweet);
    }

    @GetMapping("/{id}")
    public Tweet getTweetById(@PathVariable("id") ObjectId id) {
        return tweetRepository.findById(id.toString())
                .orElseThrow(() -> new TweetNotFoundException(id));
    }

    @PutMapping("/{id}")
    public Optional<Tweet> updateTweet(@RequestBody String newContent, @PathVariable("id") ObjectId id) {
        return tweetRepository.findById(id.toString())
                .map(tweet -> {
                    tweet.setContent(newContent);
                    tweet.setTimestamp(new Timestamp(System.currentTimeMillis()));
                    return tweetRepository.save(tweet);
                });
    }

    @DeleteMapping("/{id}")
    public void deleteTweet(@PathVariable("id") ObjectId id) {
        tweetRepository.deleteById(id.toString());
    }
}
