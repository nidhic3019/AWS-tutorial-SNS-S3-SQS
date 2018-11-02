package com.sns;

import com.amazonaws.auth.InstanceProfileCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.CreateTopicRequest;
import com.amazonaws.services.sns.model.CreateTopicResult;

public class SNSTopicCreation {

    public static void main(String[] args) {
        AmazonSNS snsClient = AmazonSNSClientBuilder.standard()
                .withCredentials(new InstanceProfileCredentialsProvider(false))
                .withRegion(Regions.US_EAST_1).build();

        createSNSTopic(snsClient);
    }

    //CREATE SNS TOPIC
    private static String createSNSTopic(AmazonSNS snsClient) {
        CreateTopicRequest createReq = new CreateTopicRequest()
                .withName("my-SNS-topic");
        CreateTopicResult result = snsClient.createTopic(createReq);
        System.out.println("ARN For Topic..." + result.getTopicArn());
        return result.getTopicArn();
    }
}
