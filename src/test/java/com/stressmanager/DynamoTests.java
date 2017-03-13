package com.stressmanager;

import java.util.HashMap;
import java.util.Map;


import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PrimaryKey;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;

import com.amazonaws.services.dynamodbv2.document.spec.GetItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.DeleteItemSpec;
import com.amazonaws.services.dynamodbv2.document.Table;

public class DynamoTests {
	static DynamoDB currentDB;

	public static void remoteDB() {
        remotetest = AmazonDynamoDBClientBuilder.standard()
            .withRegion(Regions.US_EAST_1)
            .withCredentials(new DefaultAWSCredentialsProviderChain())
            .build();
        currentDB = new DynamoDB(remotetest);

    }

	public void testUserTable() {
		remoteDB();
		DescribeTableResult result = currentDB.describeTable("Users");
		List<AttributeDefinition> att = result.getAttributeDefinitions();
		
		//check to make sure each table col exists in att
	}
}
