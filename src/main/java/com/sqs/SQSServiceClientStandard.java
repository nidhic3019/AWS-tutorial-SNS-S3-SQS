package com.sqs;


import com.amazonaws.auth.InstanceProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.CreateQueueRequest;

import java.util.HashMap;
import java.util.Map;

public class SQSServiceClientStandard {

    public static void main(String[] args) {
        AmazonSQS sqsClient = AmazonSQSClientBuilder.standard()
                .withCredentials(new InstanceProfileCredentialsProvider(false))
                .withRegion(Regions.US_EAST_1).build();

        cereateSQSStandardQueue(sqsClient);
    }

    //CREATE STANDARD QUEUE
    private static void cereateSQSStandardQueue(AmazonSQS sqsClient) {
        CreateQueueRequest queueRequest =
                new CreateQueueRequest("my-first-queue")
                        .addAttributesEntry("DelaySeconds", "10")
                        .addAttributesEntry("MessageRetentionPeriod", "72000");

        String queueUrl = sqsClient.createQueue(queueRequest).getQueueUrl();
        System.out.println("Queue Url for standard queue..." + queueUrl);
    }
}
