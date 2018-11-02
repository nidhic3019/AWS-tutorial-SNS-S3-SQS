package com.sqs;

import com.amazonaws.auth.InstanceProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.MessageAttributeValue;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageResult;

import java.util.HashMap;
import java.util.Map;

public class SQSSendMessage {

    public static void main(String[] args) {
        AmazonSQS sqsClient = AmazonSQSClientBuilder.standard()
                .withCredentials(new InstanceProfileCredentialsProvider(false))
                .withRegion(Regions.US_EAST_1).build();

        sendMessageToSQS(sqsClient);
    }

    //SEND MESSAGE TO SQS STANDARD SQS QUEUE
    private static void sendMessageToSQS(AmazonSQS sqsClient) {
        Map<String, MessageAttributeValue> messageAttributes = new HashMap<>();
        messageAttributes.put("FirstAttribute", new MessageAttributeValue()
                .withStringValue("Attribute value..")
                .withDataType("String"));

        String queueUrl = sqsClient.getQueueUrl("my-first-queue").getQueueUrl();

        SendMessageRequest messageRequest = new SendMessageRequest()
                .withQueueUrl(queueUrl).withMessageBody("Welcome..")
                .withMessageAttributes(messageAttributes);

        SendMessageResult result = sqsClient.sendMessage(messageRequest);
        System.out.println("Message Id is..." + result.getMessageId());
    }

}
