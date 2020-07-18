package com.twitter.tweet.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Timestamp;

@Document(collection = "tweets")
public class Tweet {
    @Id
    private ObjectId id;

    private String content;
    private int retweetCount;
    private int likeCount;
    private Timestamp timestamp;
    private int commentCount;
    private String[] comments;

    public Tweet(){
    }

    public Tweet(ObjectId id, String content,  int retweetCount, int likeCount, Timestamp timestamp, int commentCount, String[] comments) {
        this.id = id;
        this.content = content;
        this.retweetCount = retweetCount;
        this.likeCount = likeCount;
        this.timestamp = timestamp;
        this.commentCount = commentCount;
        this.comments = comments;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    @Override
    public String toString() {
        return "Tweet [id=" + id + ", retweetCount=" + retweetCount + ", likeCount=" + likeCount
                + ", commentCount=" + commentCount + "]";
    }
}
