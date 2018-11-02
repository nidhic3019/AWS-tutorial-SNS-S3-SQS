package com.sqs;


import com.amazonaws.auth.InstanceProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.SendMessageBatchRequest;
import com.amazonaws.services.sqs.model.SendMessageBatchRequestEntry;
import com.amazonaws.services.sqs.model.SendMessageBatchResult;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SQSSendMessageFIFO {

    public static void main(String[] args) {
        AmazonSQS sqsClient = AmazonSQSClientBuilder.standard()
                .withCredentials(new InstanceProfileCredentialsProvider(false))
                .withRegion(Regions.US_EAST_1).build();

        sendMessageInBatch(sqsClient);
    }

    //SEND MESSAGES TO FIFO QUEUE IN BATCH
    private static void sendMessageInBatch(AmazonSQS sqsClient) {
        List<SendMessageBatchRequestEntry> messageEntries = new ArrayList<>();

        messageEntries.add(new SendMessageBatchRequestEntry()
                .withId("ID-1")
                .withMessageBody("Welcome...")
                .withMessageGroupId(UUID.randomUUID().toString()));  //Required in case of FIFO
        messageEntries.add(new SendMessageBatchRequestEntry()
                .withId("ID-2")
                .withMessageBody("This is SQS tutorial")
                .withMessageGroupId(UUID.randomUUID().toString()));  //Required in case of FIFO

        String queueUrl = sqsClient.getQueueUrl("my-first-queue.fifo").getQueueUrl();

        SendMessageBatchRequest batchRequest =
                new SendMessageBatchRequest(queueUrl, messageEntries);

        SendMessageBatchResult result = sqsClient.sendMessageBatch(batchRequest);
        System.out.println("Message delivery status is..." + result.getSdkHttpMetadata().getHttpStatusCode());
    }
}
