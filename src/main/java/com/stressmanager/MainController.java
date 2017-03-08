
package com.stressmanager;

import java.util.*;

import javax.servlet.http.*;

import com.google.api.client.http.HttpTransport;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.GenericJson;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.auth.oauth2.*;
import com.google.api.services.calendar.model.*;
import com.google.api.client.auth.oauth2.Credential;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.model.*;


@RestController
@EnableOAuth2Client
public class MainController {

    // SimpleLog logger = new SimpleLog("MainController");
    //@Autowired
    //static OAuth2ClientContext oauth2ClientContext;

    //A route to just test out the Spring Framework
    @RequestMapping(value = "/ping")
    @ResponseBody
    public String ping() {
        return "Pong";
    }
    //A route to get the calendarList
    @RequestMapping(value = "/calendar/list")
    @ResponseBody
    public ResponseEntity<String> calList() throws Exception{
        final HttpHeaders httpHeaders = new HttpHeaders();
        CalendarList callist = BackendApplication.service.calendarList().list().execute();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        List<CalendarListEntry> list = callist.getItems();
        for (CalendarListEntry event : list) {
            System.out.printf("%s (%s)\n", event.getSummary(), event.getColorId());
        }

        return new ResponseEntity<String>(callist.toPrettyString(), httpHeaders, HttpStatus.OK);
    }

    //A route for setting an the calendar that is added
    @RequestMapping(value = "/calendar/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String calendarAdd(@RequestBody GenericJson request) throws Exception{

        //set up the HTTP Headers
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        //get the eventID
        System.out.println("URL: "+request.toPrettyString());
        //String stress = (String)request.get("stressValue");
        String calID = (String)request.get("calID");
        String userName = (String)request.get("userName");
        System.out.println("  "+calID + "  "+userName+"  "+DBSetup.currentDB.toString());

        //add the CalID to the user in the DB
        Table users = DBSetup.getUsersTable();
        Item item = new Item();
        item.withString("userID", userName);
        item.withString("calID", calID);
        users.putItem(item);

        //make a table for the UserID that will be store eventIDs and stress values
        int ok = DBSetup.createTable(userName);


        //return new ResponseEntity<String>(callist.toPrettyString(), httpHeaders, HttpStatus.OK);
        return "OK";
    }

    //A route for setting an event's stress by eventID
    @RequestMapping(value = "/calendar/event", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String addCalendarEvent(@RequestBody GenericJson request) throws Exception{

        //set up the HTTP Headers
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        //get the eventID
        System.out.println("URL: "+request.toPrettyString());
        String stress = (String)request.get("stressValue");
        String eventID = (String)request.get("calEvent");
        String userName = (String)request.get("userName");
        System.out.println(stress+"  "+eventID + "  "+userName);

        //get the event from the API
        int slvl= 0;
        try {
            slvl = Integer.parseInt(stress);
        } catch (Exception e) {
            System.out.println("This is not a valid stress Level from "+ userName);
            return "Level";
        }

        //add the stresslvl the user's table for events

        //cheanges the username to something usable
        userName = userName.replaceAll(" ", "_");
        try{
            Table table = DBSetup.getTable(userName);
            Item new1 = new Item();
            new1.withString("eventID", eventID);
            new1.withInt("stresslvl", slvl);
            table.putItem(new1);
            System.out.println("Table Does exist!!!");
            return "OK";
        } catch(ResourceNotFoundException e) {
            System.out.println("Table Does NOT exist!!!");
            int err = DBSetup.createTable(userName);
            if(err == 200)
                return "OK";
            return "{\"error\":\"couldn't make table \"}";

        }

        //return new ResponseEntity<String>(callist.toPrettyString(), httpHeaders, HttpStatus.OK);

    }


    //A route for setting an event by eventID
    @RequestMapping(value = "/calendar/event/details", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String calendarEventsMonth(@RequestBody GenericJson request) throws Exception{

        //set up the HTTP Headers
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        //get calendar service



        //return new ResponseEntity<String>(callist.toPrettyString(), httpHeaders, HttpStatus.OK);
        return "OK";
    }


    //A route for getting events for a particular calendar
    @RequestMapping(value = "/calendar/cal/events", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String calendarEvents(@RequestBody GenericJson request) throws Exception{

        //set up the HTTP Headers
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        //get the eventID

        //get the event from the API
        //or get it from the DB

        //return new ResponseEntity<String>(callist.toPrettyString(), httpHeaders, HttpStatus.OK);
        return "OK";
    }


}
