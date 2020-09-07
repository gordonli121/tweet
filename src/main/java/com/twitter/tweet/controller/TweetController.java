package com.twitter.tweet.controller;

import com.twitter.tweet.model.Tweet;
import com.twitter.tweet.service.TweetServiceImp;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("tweets")
public class TweetController {
    @Autowired
    private TweetServiceImp tweetService;

    @GetMapping("/all")
    @ApiOperation(value = "Get All Tweets by page and page size")
    public ResponseEntity<List<Tweet>> getTweet(@RequestParam("page") int page,
                                                @RequestParam("pageSize") int size) throws Exception{
        Pageable request = PageRequest.of(page, size);
        List<Tweet> result = tweetService.findAll(request);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Tweet> postTweet(@RequestBody Tweet tweet) throws Exception
    {
        Tweet created = tweetService.createTweet(tweet);
        return new ResponseEntity<>(created, HttpStatus.CREATED);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Tweet> getTweetById(@PathVariable("id") String id) throws Exception {
        Tweet tweet = tweetService.findById(id);
        return new ResponseEntity<>(tweet, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tweet> updateTweet(@PathVariable("id") String id, @RequestBody String newContent) throws Exception {
        Tweet tweet = tweetService.updateTweet(id, newContent);
        return new ResponseEntity<>(tweet, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Tweet> deleteTweet(@PathVariable("id") String id) throws Exception {
        Tweet deleted = tweetService.deleteById(id);
        return new ResponseEntity<>(deleted, HttpStatus.OK);
    }

}
