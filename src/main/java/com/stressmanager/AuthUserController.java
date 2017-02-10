package com.stressmanager;

import java.util.List;
import javax.validation.Valid;
import com.google.gson.Gson;

//Spring FW
import org.springframework.web.bind.annotation.*;
//java
import java.util.*;

@RestController
@RequestMapping(value = "/api/auth")
public class AuthUserController {

	//Will recieve the data of the user, then push it to the DB
    @RequestMapping(method = RequestMethod.POST)
	public String auth(@RequestBody AuthUser input) {
		 Gson g = new Gson();
		 AuthUser u = new AuthUser(input);
		 String str = g.toJson(u);

          //TODO: Do the OAUth thing a get a token

		 //TODO: Add to the DB

		 //TODO: send Confirmation and Token
		 return str;
	}

    // @RequestMapping(method = RequestMethod.GET)
    // public String getUser(@RequestBody AuthUser input) {
    //      Gson g = new Gson();
    //      AuthUser u = new AuthUser(input);
    //      String str = g.toJson(u);
    //
    //       //TODO: Do the OAuth thing a get a token
    //
    //      //TODO: Add to the DB
    //
    //      //TODO: send Confirmation and Token
    //      return str;
    // }

}
