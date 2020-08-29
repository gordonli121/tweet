package com.twitter.tweet.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
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

    @Override
    public String toString() {
        return "Tweet [id=" + id + ", retweetCount=" + retweetCount + ", likeCount=" + likeCount
                + ", commentCount=" + commentCount + "]";
    }
}
