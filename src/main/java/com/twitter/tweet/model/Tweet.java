package com.twitter.tweet.model;

import org.springframework.data.annotation.Id;

import java.sql.Array;
import java.sql.Timestamp;

public class Tweet {
    @Id
    private Long id;

    private int retweetCount;
    private int likeCount;
    private Timestamp timestamp;
    private int commentCount;
    private String[] comments;

    public Tweet(int retweetCount, int likeCount, Timestamp timestamp, int commentCount, String[] comments) {
        this.retweetCount = retweetCount;
        this.likeCount = likeCount;
        this.timestamp = timestamp;
        this.commentCount = commentCount;
        this.comments = comments;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getRetweetCount() {
        return retweetCount;
    }

    public void setRetweetCount(int retweetCount) {
        this.retweetCount = retweetCount;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public String[] getComments() {
        return comments;
    }

    public void setComments(String[] comments) {
        this.comments = comments;
    }
}
