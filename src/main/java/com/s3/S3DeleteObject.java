/**
 * “Unpublished Work © 2018 Deere & Company. All Worldwide Rights Reserved. THIS MATERIAL IS THE PROPERTY OF DEERE & COMPANY. ALL USE, ALTERATIONS, DISCLOSURE, DISSEMINATION AND/OR REPRODUCTION NOT SPECIFICALLY AUTHORIZED BY DEERE & COMPANY IS PROHIBITED. “
 */
package com.s3;

import com.amazonaws.auth.InstanceProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

public class S3DeleteObject {

    private static final String BUCKET = "my-s3-bucket";

    public static void main(String[] args) {
        AmazonS3 amazonS3 = AmazonS3ClientBuilder.standard()
                .withRegion(Regions.US_EAST_1)
                .withCredentials(new InstanceProfileCredentialsProvider(false))
                .build();

        amazonS3.deleteObject(BUCKET, "HelloWorld.txt");

        System.out.println("Objects Deleted from Bucket.");
    }
}
