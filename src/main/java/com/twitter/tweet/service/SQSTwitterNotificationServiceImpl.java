package com.twitter.tweet.service;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.twitter.tweet.message.TwitterNotification;
import com.twitter.tweet.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class SQSTwitterNotificationServiceImpl implements TweetMessageService{
    @Value("${spring.aws.sqsUrl}")
    private String sqsUrl;

    @Value("${spring.aws.accessKey}")
    private String awsAccessKey;

    @Value("${spring.aws.accessKeySecret}")
    private String awsSecretKey;

    @Value("${spring.aws.region}")
    private String awsRegion;

    private AmazonSQS amazonSQS;

    @Autowired
    public SQSTwitterNotificationServiceImpl(
            @Value("${spring.aws.accessKey}") String awsAccessKey,
            @Value("${spring.aws.accessKeySecret}") String awsSecretKey,
            @Value("${spring.aws.region}") String awsRegion
            ) {
        AWSCredentialsProvider awsCredentialsProvider = new AWSStaticCredentialsProvider(
                new BasicAWSCredentials(awsAccessKey, awsSecretKey)
        );

        AmazonSQS amazonSQS = AmazonSQSClientBuilder.standard().withCredentials(awsCredentialsProvider).withRegion(awsRegion).build();
        this.amazonSQS = amazonSQS;
    }

    @Override
    public void sendMessage(TwitterNotification tn) throws JsonProcessingException {
        String msg;
        msg = JsonUtils.convertTwitterNotificationMessage(tn);
        SendMessageRequest send_msg_request = new SendMessageRequest()
                .withQueueUrl(sqsUrl)
                .withMessageBody(msg)
                .withDelaySeconds(5);
        this.amazonSQS.sendMessage(send_msg_request);
    }
}
