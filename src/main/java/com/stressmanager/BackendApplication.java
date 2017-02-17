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
@EnableAuthorizationServer
@SpringBootApplication
@EnableAutoConfiguration
@Order(6)
public class BackendApplication extends WebSecurityConfigurerAdapter {

	@Autowired
	OAuth2ClientContext oauth2ClientContext;

	//set up google credz
	static com.google.api.services.calendar.Calendar service;

	//static Credentials credz;

	@RequestMapping({ "/user", "/me" })
	public Map<String, String> user(Principal principal) throws Exception{
		Map<String, String> map = new LinkedHashMap<>();
		map.put("name", principal.getName());
		map.put("auth", oauth2ClientContext.getAccessToken().toString());

		System.out.println();
		System.out.println();
		System.out.println("========================================");
		System.out.println(oauth2ClientContext.getAccessToken().toString());
		System.out.println("========================================");
		System.out.println("authenticated!!!!");

		service = MainController.getCalendarService();

		DateTime now = new DateTime(System.currentTimeMillis());
		Events events = service.events().list("primary")
			.setMaxResults(10)
			.setTimeMin(now)
			.setOrderBy("startTime")
			.setSingleEvents(true)
			.execute();

		return map;
	}


	// @Bean
    // public RequestHeaderAuthenticationFilter requestHeaderAuthenticationFilter(
    //         final AuthenticationManager authenticationManager) {
    //     RequestHeaderAuthenticationFilter filter = new MyRequestHeaderAuthenticationFilter();
    //     filter.setAuthenticationManager(authenticationManager);
    //     filter.setExceptionIfHeaderMissing(false);
    //     filter.setInvalidateSessionOnPrincipalChange(true);
    //     filter.setCheckForPrincipalChanges(true);
    //     filter.setContinueFilterChainOnUnsuccessfulAuthentication(false);
    //     return filter;
    // }

	///temp call to Google Calendar API
	@RequestMapping(value="/me/calendar")
	public String calendar(String str) throws Exception{

		HttpURLConnection connection = null;
		try {

			String url = "https://www.googleapis.com/calendar/v3/users/me/calendarList?key=AIzaSyDoVkWadSYb9GA8zT-ZVMvHDovYk1N-P98";

			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();

			// optional default is GET
			con.setRequestMethod("GET");

			//add request header
			//con.setRequestProperty("User-Agent", USER_AGENT);

			int responseCode = con.getResponseCode();
			System.out.println("\nSending 'GET' request to URL : " + url);
			System.out.println("Response Code : " + responseCode);

			BufferedReader in = new BufferedReader(
					new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}

			//Send the Response
			System.out.println(response.toString());

			Data new1 = new Data(response.toString());
			Gson json = new Gson();
			System.out.println("========================================");
			System.out.println("========================================");
			System.out.println(response.toString());
			System.out.println("========================================");
			System.out.println("========================================");


			return json.toJson(new1);

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}


	}

	@RequestMapping(value = "/me/calendar/events")
	public String events() throws Exception {
		return "";
	}


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// @formatter:off
		http.antMatcher("/**").authorizeRequests().antMatchers("/", "/login**", "/webjars/**").permitAll().anyRequest()
				.authenticated().and().exceptionHandling()
				.authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/")).and().logout()
				.logoutSuccessUrl("/").permitAll().and().csrf()
				.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).and()
				.addFilterBefore(ssoFilter(), BasicAuthenticationFilter.class);
		// @formatter:on
	}

	@Configuration
	@EnableResourceServer
	protected static class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
		@Override
		public void configure(HttpSecurity http) throws Exception {
			// @formatter:off
			http.antMatcher("/me").authorizeRequests().anyRequest().authenticated();
			// @formatter:on
		}
	}

	public static void main(String[] args) {
		//TODO: GENERATE YML FILE AND CLIENT ID
		SpringApplication.run(BackendApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean oauth2ClientFilterRegistration(OAuth2ClientContextFilter filter) {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(filter);
		registration.setOrder(-100);
		return registration;
	}

	@Bean
	@ConfigurationProperties("google")
	public ClientResources google() {
		return new ClientResources();
	}

	private Filter ssoFilter() {
		CompositeFilter filter = new CompositeFilter();
		List<Filter> filters = new ArrayList<>();
		filters.add(ssoFilter(google(), "/login/google"));
		filter.setFilters(filters);
		return filter;
	}

	private Filter ssoFilter(ClientResources client, String path) {
		OAuth2ClientAuthenticationProcessingFilter filter = new OAuth2ClientAuthenticationProcessingFilter(
				path);
		OAuth2RestTemplate template = new OAuth2RestTemplate(client.getClient(), oauth2ClientContext);
		filter.setRestTemplate(template);
		UserInfoTokenServices tokenServices = new UserInfoTokenServices(
				client.getResource().getUserInfoUri(), client.getClient().getClientId());
		tokenServices.setRestTemplate(template);
		filter.setTokenServices(tokenServices);
		return filter;
	}

}
class MyRequestHeaderAuthenticationFilter extends RequestHeaderAuthenticationFilter {

	   @Override
	   protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			   AuthenticationException failed) {
		  try{
		   super.unsuccessfulAuthentication(request, response, failed);
		   // see comments in Servlet API around using sendError as an alternative
		   response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

	   } catch (Exception e) {
		   e.printStackTrace();
	   }
	   }
   }

class ClientResources {

	@NestedConfigurationProperty
	private AuthorizationCodeResourceDetails client = new AuthorizationCodeResourceDetails();

	@NestedConfigurationProperty
	private ResourceServerProperties resource = new ResourceServerProperties();


	public ClientResources() {
		client.setClientId(System.getenv("GOOGLE_CLIENT_ID"));
		client.setClientSecret(System.getenv("GOOGLE_CLIENT_SECRET"));
		List<String> str = new ArrayList<>();
		str.add(CalendarScopes.CALENDAR);
		client.setScope(str);
	}

	public AuthorizationCodeResourceDetails getClient() {
		return client;
	}

	public ResourceServerProperties getResource() {
		return resource;
	}
}
