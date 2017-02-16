package com.stressmanager;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.apache.commons.logging.impl.SimpleLog;
import com.google.gson.Gson;
import java.io.*;
import java.net.*;

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

    ///temp call to Google Calendar API
    @RequestMapping(value="/calendar")
    public String calendar() throws Exception {

        System.out.println("========================================");
        //System.out.println(User.accessToken.toString());
        System.out.println("========================================");

        HttpURLConnection connection = null;
        try {

                String url = "https://www.googleapis.com/calendar/v3/users/me/calendarList";
        //
               URL obj = new URL(url);
        //     HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        //
        //     // optional default is GET
        //     con.setRequestMethod("GET");
        //
        //     //add request header
        //     //con.setRequestProperty("User-Agent", USER_AGENT);
        //
        //     int responseCode = con.getResponseCode();
        //     System.out.println("\nSending 'GET' request to URL : " + url);
        //     System.out.println("Response Code : " + responseCode);
        //
        //     BufferedReader in = new BufferedReader(
        //         new InputStreamReader(con.getInputStream()));
        //     String inputLine;
        //     StringBuffer response = new StringBuffer();
        //
        //     while ((inputLine = in.readLine()) != null) {
        //         response.append(inputLine);
        //     }
        //
        //     //Send the Response
        //     System.out.println(response.toString());
        //     logger.debug(response.toString());
        //     Data new1 = new Data(response.toString());
        //     Gson json = new Gson();
        //
        //     return json.toJson(new1);
        } catch (Exception e) {
                e.printStackTrace();
                return null;
        }
        return "";

    }



    //POST that takes calID and request calendar from API and parse it.
    // Send "OK" as a response.

}
