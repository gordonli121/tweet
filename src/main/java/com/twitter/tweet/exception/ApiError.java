package com.twitter.tweet.exception;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiError {
    private String source = "";
    private String message = "";
}
