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
    // config basic AWS setup before accessing the tables like validating credentials

    // DynamoDB dynamoDB = new DynamoDB(new AmazonDynamoDBClient(
    // new ProfileCredentialsProvider()));

    static AmazonDynamoDBClient localtest = new AmazonDynamoDBClient()
    .withEndpoint("http://localhost:8080");

    static AmazonDynamoDBClient remotetest = new AmazonDynamoDBClient()
    .withEndpoint("http://dynamodb.us-east-2.amazonaws.com");

    static DynamoDB currentDB;

    public static void localDB() {
        currentDB = new DynamoDB(localtest);
    }

    public static void remoteDB() {
        currentDB = new DynamoDB(remotetest);
    }

    public Table getUsersTable() {
        return currentDB.getTable("Users");
    }

    public static void main (String[] args) {

    }
}
