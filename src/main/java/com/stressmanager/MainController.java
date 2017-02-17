package com.stressmanager;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.apache.commons.logging.impl.SimpleLog;

import com.google.gson.Gson;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.*;
import com.google.api.client.json.GenericJson;


import java.io.*;
import java.net.*;
import java.util.List;


@Controller
@Component
public class MainController {

    SimpleLog logger = new SimpleLog("MainController");

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

    //POST that takes calID and request calendar from API and parse it.
    // Send "OK" as a response.

}
