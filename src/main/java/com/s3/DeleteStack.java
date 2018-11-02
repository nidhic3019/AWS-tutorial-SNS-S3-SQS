
package com.s3;

import com.amazonaws.auth.InstanceProfileCredentialsProvider;
import com.amazonaws.services.cloudformation.AmazonCloudFormation;
import com.amazonaws.services.cloudformation.AmazonCloudFormationClientBuilder;
import com.amazonaws.services.cloudformation.model.DeleteStackRequest;

public class DeleteStack {

    public static void main(String[] args) {
        AmazonCloudFormation amazonCloudFormation = AmazonCloudFormationClientBuilder.standard()
                .withCredentials(new InstanceProfileCredentialsProvider(false))
                .withRegion("us-east-1").build();

        DeleteStackRequest deleteStackRequest = new DeleteStackRequest()
                .withStackName("S3Bucket");


        amazonCloudFormation.deleteStack(deleteStackRequest);

        System.out.println("Stack deleted successfully.");

    }
}
