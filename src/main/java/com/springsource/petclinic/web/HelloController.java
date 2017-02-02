package com.springsource.petclinic.web;

import org.springframework.web.servlet.ModelAndView;



@Controller
public class HelloController {
   private String message;

   public void setMessage(String message){
      this.message  = message;
   }


   @RequestMapping("/")
   public ModelAndView starting() {
        String message = "WELCOME SPRING MVC";
        return new ModelAndView("welcomepage", "message", message);
   }
   public void getMessage(){
      System.out.println("Your Message : " + message);
   }
}
