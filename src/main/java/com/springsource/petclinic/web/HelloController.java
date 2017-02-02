package com.springsource.petclinic.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;



@Controller
public class HelloController {
   private String message;
   @RequestMapping("/")
   public ModelAndView starting() {
        String message = "WELCOME SPRING MVC";
        return new ModelAndView("welcomepage", "message", message);
   }

}
