package com.sns;


import com.amazonaws.auth.InstanceProfileCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.ListTopicsResult;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;

import java.util.Date;

public class SNSPublishMessage {

    public static void main(String[] args) {
        AmazonSNS snsClient = AmazonSNSClientBuilder.standard()
                .withCredentials(new InstanceProfileCredentialsProvider(false))
                .withRegion(Regions.US_EAST_1).build();

        publishToTopic(snsClient);
    }

    //PUBLISH MESSAGE TO TOPIC
    private static void publishToTopic(AmazonSNS snsClient) {
        ListTopicsResult listTopicsResult = snsClient.listTopics();
        String topicArn = listTopicsResult.getTopics().get(0).getTopicArn();

        PublishRequest publishReq = new PublishRequest()
                .withTopicArn(topicArn)
                .withMessage("Notification sent: Have a good day! " + new Date());
        PublishResult result = snsClient.publish(publishReq);
        System.out.println("Message Id is... " + result.getMessageId());
    }
}
