package com.sqs;


import com.amazonaws.auth.InstanceProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.DeleteQueueResult;

public class SQSDeleteQueue {
    public static void main(String[] args) {
        AmazonSQS sqsClient = AmazonSQSClientBuilder.standard()
                .withCredentials(new InstanceProfileCredentialsProvider(false))
                .withRegion(Regions.US_EAST_1).build();

        deleteSQSQueue(sqsClient);
    }

    //DELETE SQS QUEUE
    private static void deleteSQSQueue(AmazonSQS sqsClient) {
        String queueUrl = sqsClient.getQueueUrl("my-first-queue").getQueueUrl();
        String fifoQueueUrl = sqsClient.getQueueUrl("my-first-queue.fifo").getQueueUrl();

        DeleteQueueResult result = sqsClient.deleteQueue(queueUrl);
        System.out.println("Deletion status for Standard Queue is..." + result.getSdkHttpMetadata().getHttpStatusCode());
        DeleteQueueResult result1 = sqsClient.deleteQueue(fifoQueueUrl);
        System.out.println("Deletion status for FIFO Queue is..." + result1.getSdkHttpMetadata().getHttpStatusCode());
    }
}
