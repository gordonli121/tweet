package com.twitter.tweet.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
@Data
@AllArgsConstructor
public class PersistenceException extends Exception{
    public static enum ExceptionType {
        NO_CONTENT,
        DUPLICATE,
        GENERIC,
        NOT_FOUND
    }
    String message;
    ExceptionType type;
    String source;
}
