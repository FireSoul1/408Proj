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
    @Autowired 
    static OAuth2ClientContext oauth2ClientContext;

    //A route to just test out the Spring Framework
    @RequestMapping(value = "/ping")
    // @ResponseBody
    public String ping() {
        return "Pong";
    }
    //A route to just test out the Spring Framework
    @RequestMapping(value = "/calendar/list")
    @ResponseBody
    
    public static Credential authorize() throws Exception {
		final List<String> SCOPES = Arrays.asList(CalendarScopes.CALENDAR);

		TokenResponse tolkien = new TokenResponse();
		tolkien.setAccessToken(oauth2ClientContext.getAccessToken().toString());

		Credential credz = new Credential(BearerToken.authorizationHeaderAccessMethod())
			.setFromTokenResponse(tolkien);
		System.out.println("authorized!!!");
		return credz;

	}

	public static com.google.api.services.calendar.Calendar getCalendarService() throws Exception {
		final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
		HttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
		Credential credz = authorize();

		return new com.google.api.services.calendar.Calendar.Builder(
			HTTP_TRANSPORT, JSON_FACTORY, credz)
			.setApplicationName("Stressmanager")
			.build();
	}

	public String pingTemp() throws Exception{
        CalendarList callist = BackendApplication.service.calendarList().list().execute();

		List<CalendarListEntry> list = callist.getItems();
		for (CalendarListEntry event : list) {
		     System.out.printf("%s (%s)\n", event.getSummary(), event.getColorId());
		}

        return callist.toPrettyString();
    }

}
