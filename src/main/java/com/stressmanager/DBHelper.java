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

public class DBHelper {
    // Adds stuff to tables and sets them up and accesses them
    // inserts users into table

    // DynamoDB dynamoDB = new DynamoDB(new AmazonDynamoDBClient(
    //     new ProfileCredentialsProvider()));

    DBSetup setup = new DBSetup();;
    
    public void accessDB() {
        setup.remoteDB();
    }

    public void addToTable(String username, String authToken) {
        Table users = setup.getUsersTable();

        PutItemOutcome outcome = users.putItem(new Item()
                .withPrimaryKey("username", username, "authToken", authToken));
    }


    void removeFromTable(String username, String authToken) {
        Table users = setup.getUsersTable();

        DeleteItemSpec deleteItemSpec = new DeleteItemSpec()
            .withPrimaryKey("username", username, "authToken", authToken);
    }
    
    public static void main(String[] args) throws Exception {


    }
}
