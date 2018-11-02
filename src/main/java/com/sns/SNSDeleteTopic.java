package com.sns;


import com.amazonaws.auth.InstanceProfileCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.DeleteTopicRequest;
import com.amazonaws.services.sns.model.DeleteTopicResult;
import com.amazonaws.services.sns.model.ListTopicsResult;
import com.amazonaws.services.sns.model.Topic;

import java.util.List;

public class SNSDeleteTopic {

    public static void main(String[] args) {
        AmazonSNS snsClient = AmazonSNSClientBuilder.standard()
                .withCredentials(new InstanceProfileCredentialsProvider(false))
                .withRegion(Regions.US_EAST_1).build();

        deleteSubscription(snsClient);
    }

    //DELETE SNS TOPIC
    private static void deleteSubscription(AmazonSNS snsClient) {
        ListTopicsResult listTopicsResult = snsClient.listTopics();

        String topicArn = listTopicsResult.getTopics().get(0).getTopicArn();
        DeleteTopicRequest request = new DeleteTopicRequest().withTopicArn(topicArn);

        DeleteTopicResult result = snsClient.deleteTopic(request);
        System.out.println("Deletion done with status Code... " + result.getSdkHttpMetadata().getHttpStatusCode());
    }
}
