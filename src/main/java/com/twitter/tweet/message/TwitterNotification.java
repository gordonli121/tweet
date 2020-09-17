package com.twitter.tweet.message;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
public class TwitterNotification {
    String messageId;
    String sentFrom;
    Date timeSent = new Date();
    String sender;
    String receiver;
    MessageType messageType;
    String content;

    enum MessageType {
        MENTION,
        LIKE,
        CHAT
    }
}
