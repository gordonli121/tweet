package com.twitter.tweet.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Document(collection = "tweets")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
@NoArgsConstructor
public class Tweet {
    @Id
    private String id;
    private String content = "";
    private int retweetCount = 0;
    private int likeCount = 0;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date timestamp = new Date();
    private int commentCount = 0;
    private List<String> comments = new ArrayList<>();
    private String publisher;
    private List<String> mentionedUsers;
    @Override
    public String toString() {
        return "Tweet [id=" + id + ", retweetCount=" + retweetCount + ", likeCount=" + likeCount
                + ", commentCount=" + commentCount + "]";
    }
}
