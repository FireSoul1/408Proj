package com.stressmanager;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PrimaryKey;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;

public class DBSetup {
    //used for Localtesting
    static AmazonDynamoDBClient localtest = new AmazonDynamoDBClient()
        .withEndpoint("http://localhost:8080");
    //used for remote testing
    static AmazonDynamoDBClient remotetest = new AmazonDynamoDBClient()
        .withEndpoint("http://dynamodb.us-east-2.amazonaws.com");
    //the current instance of DynamoDB
    static DynamoDB currentDB;

    public static void localDB() {
        currentDB = new DynamoDB(localtest);
    }

    public static void remoteDB() {
        currentDB = new DynamoDB(remotetest);
    }

    public static Table getUsersTable() {
        return currentDB.getTable("Users");
    }

}
