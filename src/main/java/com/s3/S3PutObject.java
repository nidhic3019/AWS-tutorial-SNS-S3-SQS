package com.s3;

import com.amazonaws.auth.InstanceProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;

import java.io.File;

public class S3PutObject {

    private static final String BUCKET = "my-s3-bucket";

    public static void main(String[] args) {
        AmazonS3 amazonS3 = AmazonS3ClientBuilder.standard()
                .withRegion(Regions.US_EAST_1)
                .withCredentials(new InstanceProfileCredentialsProvider(false))
                .build();

        File file = new File("HelloWorld.txt");
        PutObjectRequest putObjectRequest = new PutObjectRequest(BUCKET, "HelloWorld.txt", file);
        PutObjectResult result = amazonS3.putObject(putObjectRequest);

        System.out.println("ETag : " + result.getETag());
    }
}
