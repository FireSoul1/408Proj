package com.stressmanager;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.Principal;
import java.util.*;

import javax.servlet.Filter;
import javax.servlet.http.*;

import com.google.api.client.http.HttpTransport;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.client.googleapis.compute.ComputeCredential.Builder;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.auth.oauth2.*;
import com.google.gson.Gson;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.*;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.client.util.DateTime;

import org.springframework.security.core.*;
import org.springframework.security.web.authentication.preauth.RequestHeaderAuthenticationFilter;
import org.springframework.security.web.authentication.preauth.RequestHeaderAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.filter.CompositeFilter;
import org.springframework.boot.autoconfigure.*;
import org.springframework.security.oauth2.client.resource.*;

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

}
