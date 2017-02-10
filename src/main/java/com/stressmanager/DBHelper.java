package com.stressmanager;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PrimaryKey;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
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
        //TODO add user's name, auth token, and other stuff to the table
    }


     void removeFromTable() {

    }
    
    public static void main(String[] args) throws Exception {


    }
}
