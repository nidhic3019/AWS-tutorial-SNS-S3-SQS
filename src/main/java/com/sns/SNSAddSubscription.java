package com.sns;


import com.amazonaws.auth.InstanceProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.ListTopicsResult;
import com.amazonaws.services.sns.model.SubscribeRequest;
import com.amazonaws.services.sns.model.SubscribeResult;

public class SNSAddSubscription {

    public static void main(String[] args) {
        AmazonSNS snsClient = AmazonSNSClientBuilder.standard()
                .withCredentials(new InstanceProfileCredentialsProvider(false))
                .withRegion(Regions.US_EAST_1).build();

        addSubscription(snsClient, args[0]);
    }

    //ADD SUBSCRIPTION ON SNS TOPIC
    private static void addSubscription(AmazonSNS snsClient, String address) {
        ListTopicsResult listTopicsResult = snsClient.listTopics();

        String topicArn = listTopicsResult.getTopics().get(0).getTopicArn();
        SubscribeResult result = snsClient.subscribe(new SubscribeRequest(topicArn, "email", address));
        System.out.println("ARN for subscription.. " + result.getSubscriptionArn());
    }
}
