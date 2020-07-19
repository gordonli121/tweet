package com.twitter.tweet.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Document(collection = "tweets")
public class Tweet {
    @Id
    private String id;
    private String content;
    private int retweetCount;
    private int likeCount;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date timestamp = new Date();
    private int commentCount;
    private List<String> comments;

    public Tweet(){
    }

    public Tweet(String content,  int retweetCount, int likeCount, int commentCount, List<String> comments) {
        this.content = content;
        this.retweetCount = retweetCount;
        this.likeCount = likeCount;
//        String pattern = "yyyyMMddHHmmssms";
//        DateFormat df = new SimpleDateFormat(pattern);
//        Date today = Calendar.getInstance().getTime();
//        this.timestamp = new SimpleDateFormat("yyyyMMddHHmmssms").format(Calendar.getInstance().getTime());
        this.commentCount = commentCount;
        this.comments = comments;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public List<String> getComments() {
        return comments;
    }

    @Override
    public String toString() {
        return "Tweet [id=" + id + ", retweetCount=" + retweetCount + ", likeCount=" + likeCount
                + ", commentCount=" + commentCount + "]";
    }
}
