package com.stressmanager;

import java.util.*;

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

import java.util.List;

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
    //A route to just test out the Spring Framework
    @RequestMapping(value = "/calendar/list")
    @ResponseBody
    public String pingTemp() throws Exception{
        CalendarList callist = BackendApplication.service.calendarList().list().execute();

        List<CalendarListEntry> list = callist.getItems();
        for (CalendarListEntry event : list) {
             System.out.printf("%s (%s)\n", event.getSummary(), event.getColorId());
        }

        return callist.toPrettyString();
    }
}
