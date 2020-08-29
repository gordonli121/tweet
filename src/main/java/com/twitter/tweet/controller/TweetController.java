package com.twitter.tweet.controller;

import com.twitter.tweet.model.Tweet;
import com.twitter.tweet.service.TweetServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("tweets")
public class TweetController {
    @Autowired
    private TweetServiceImp tweetService;

    @GetMapping
    public ResponseEntity<List<Tweet>> getTweet(){
        return tweetService.findAll();
    }

    @PostMapping
    public ResponseEntity<Tweet> postTweet(@RequestBody Tweet tweet){
        return tweetService.postTweet(tweet);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tweet> getTweetById(@PathVariable("id") String id) {
        return tweetService.findById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tweet> updateTweet(@PathVariable("id") String id, @RequestBody String newContent) {
        return tweetService.updateTweet(id, newContent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteTweet(@PathVariable("id") String id) {
        return tweetService.deleteById(id);
    }
}
