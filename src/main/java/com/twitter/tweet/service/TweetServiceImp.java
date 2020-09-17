package com.twitter.tweet.service;

import com.twitter.tweet.exception.PersistenceException;
import com.twitter.tweet.message.TwitterMentionNotification;
import com.twitter.tweet.model.Tweet;
import com.twitter.tweet.repository.TweetRepository;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class TweetServiceImp implements TweetService{
    @Autowired
    private TweetRepository tweetRepository;
    @Autowired
    private TweetMessageService tweetMessageService;
    @Override
    public List<Tweet> findAll(Pageable request) throws Exception {
        Page<Tweet> tweets = tweetRepository.findAll(request);
        log.info("Getting tweets with " + request);
        if (tweets.isEmpty()) {
            log.warn("No tweets exists");
            throw new PersistenceException("No Tweets found", PersistenceException.ExceptionType.NO_CONTENT, this.getClass().getSimpleName());
        }
        List<Tweet> _tweets = tweets.getContent();
        log.debug("Got tweets: " + _tweets);
        return _tweets;
    }

    @Override
    public Tweet findById(String id) throws Exception {
        log.info("Getting tweet with id:" + id);
        Optional<Tweet> tweetData = tweetRepository.findById(id);
        if (tweetData.isEmpty() ) {
            log.error("No tweet is found with id:" + id);
            throw new PersistenceException("No Tweet found with Id:" + id, PersistenceException.ExceptionType.NOT_FOUND, this.getClass().getSimpleName());
        }
        Tweet _tweet = tweetData.get();
        log.debug("Retrieved tweet: " + _tweet);
        return _tweet;
    }

    @Override
    public Tweet createTweet(Tweet tweet) throws Exception {
        log.info("Creating tweet: " + tweet);
        tweetRepository.save(tweet);
        for (String user : tweet.getMentionedUsers()) {
            TwitterMentionNotification tmn = new TwitterMentionNotification();
            tmn.setContent(tweet.getContent());
            tmn.setReceiver(user);
            tmn.setSender(tweet.getPublisher());
            tmn.setSentFrom("Tweet-Service");
            tmn.setMessageId(tweet.getId());
            try {tweetMessageService.sendMessage(tmn);}
                catch (Exception e) {
                    log.error(e.getLocalizedMessage());
                }
        }
        log.info("Created tweet :" + tweet);
        return tweet;

    }

    @Override
    public Tweet updateTweet(String id, String content) throws Exception {
        log.info("Updating tweet with id: " + id);
        Optional<Tweet> tweetData = tweetRepository.findById(id);

        if (tweetData.isPresent()) {
            Tweet _tweet = tweetData.get();
            _tweet.setContent(content);
            _tweet.setTimestamp(new Date());
            log.debug("Updated tweet with " + _tweet);
            return _tweet;
        }
        else {
            log.error("Cannot find tweet with message id:" + id);
            throw new
                    PersistenceException("Cannot find twitte by message id:" + id, PersistenceException.ExceptionType.NOT_FOUND, this.getClass().getSimpleName());
        }
    }

    @Override
    public Tweet deleteById(String id) throws Exception {
        log.info("Deleting twitt with id:" + id);
        Tweet toDelete = findById(id);
        tweetRepository.deleteById(id);
        log.info("Deleted twitte: " + toDelete);
        return toDelete;

    }
}
