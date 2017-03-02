
package com.stressmanager;

import java.util.*;

import javax.servlet.http.*;

import com.google.api.client.http.HttpTransport;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.client.json.JsonFactory;
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
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;


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
    //TODO: A route for posting Event with it's value


    //A route for getting an event by eventID
    @RequestMapping(value = "/calendar/event")
    @ResponseBody
    public String calendarEvent(HttpServletRequest request) throws Exception{

        //get the eventID

        //get the event from the API

        //return the JSON with the information
        return "Cool beans";
    }



}
