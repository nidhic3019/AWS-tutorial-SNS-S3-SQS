
package com.s3;


import com.amazonaws.auth.InstanceProfileCredentialsProvider;
import com.amazonaws.services.cloudformation.AmazonCloudFormation;
import com.amazonaws.services.cloudformation.AmazonCloudFormationClientBuilder;
import com.amazonaws.services.cloudformation.model.CreateStackRequest;
import com.amazonaws.services.cloudformation.model.CreateStackResult;

public class CreateStack {

    public static void main(String[] args) {
        AmazonCloudFormation amazonCloudFormation = AmazonCloudFormationClientBuilder.standard()
                .withCredentials(new InstanceProfileCredentialsProvider(false))
                .withRegion("us-east-1").build();

        CreateStackRequest createStackRequest = new CreateStackRequest()
                .withStackName("S3Bucket")
                .withTemplateBody("S3.template");

        CreateStackResult createStackResult = amazonCloudFormation.createStack(createStackRequest);

        System.out.println("Stack with Id : " + createStackResult.getStackId() +" created successfully.");
    }
}
