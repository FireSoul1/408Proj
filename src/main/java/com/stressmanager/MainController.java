package com.stressmanager;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Component
public class MainController {

    //A route to just test out the Spring Framework
    @RequestMapping(value = "/ping")
    @ResponseBody
    public String ping() {
        return "Pong";
    }

    //POST that takes calID and request calendar from API and parse it.
    // Send "OK" as a response.

}
