package com.stressmanager;

import java.security.Principal;
import java.util.*;

import javax.servlet.Filter;
import javax.servlet.http.*;

import com.google.api.client.http.HttpTransport;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.auth.oauth2.*;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.*;

import org.springframework.security.core.*;
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
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.filter.CompositeFilter;
import org.springframework.boot.autoconfigure.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.http.HttpStatus;

import com.amazonaws.services.dynamodbv2.document.DynamoDB;		
import com.amazonaws.services.dynamodbv2.document.Item;		
import com.amazonaws.services.dynamodbv2.document.Table;		
import com.amazonaws.services.dynamodbv2.document.spec.GetItemSpec;



@RestController
@EnableOAuth2Client
@EnableAuthorizationServer
@SpringBootApplication
@EnableAutoConfiguration
@Order(6)
public class BackendApplication extends WebSecurityConfigurerAdapter {

	@Autowired
	OAuth2ClientContext oauth2ClientContext;

	DBHelper db = new DBHelper();

	//static Credentials credz;

	static com.google.api.services.calendar.Calendar service;
//	static OAuth2AccessToken token;

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

		service = getCalendarService();

		DateTime now = new DateTime(System.currentTimeMillis());

		Events events = service.events().list("primary")
			.setMaxResults(10)
			.setTimeMin(now)
			.setOrderBy("startTime")
			.setSingleEvents(true)
			.execute();

		List<Event> items = events.getItems();
		if (items.size() == 0) {
			System.out.println("No upcoming events found.");
		}
		else {
			System.out.println("Upcoming events");
			for (Event event : items) {
				DateTime start = event.getStart().getDateTime();
				if (start == null) {
					start = event.getStart().getDate();
				}
				System.out.printf("%s (%s)\n", event.getSummary(), start);
			}
		}

		return map;///list.get(1).getColorId();
	}

	public Credential authorize() throws Exception {
		final List<String> SCOPES =
        	Arrays.asList(CalendarScopes.CALENDAR);

		TokenResponse tolkien = new TokenResponse();
		tolkien.setAccessToken(oauth2ClientContext.getAccessToken().toString());

		Credential credz = new Credential(BearerToken.authorizationHeaderAccessMethod())
			.setFromTokenResponse(tolkien);
		System.out.println("authorized!!!");
		return credz;

	}

	public com.google.api.services.calendar.Calendar getCalendarService() throws Exception {
		final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
		HttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
		Credential credz = authorize();

		return new com.google.api.services.calendar.Calendar.Builder(
			HTTP_TRANSPORT, JSON_FACTORY, credz)
			.setApplicationName("Stressmanager")
			.build();
	}
	
	
	//TODO: Possible custom Error Codes and pages. This Bean will be needed
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
	@RequestMapping(value="/calendar")
	public String calendar(String str) throws Exception{

		return "This is where the cal go";

	}

	@RequestMapping(value = "/me/calendar/events")
	public ResponseEntity<String> events() throws Exception {
		//HTTP Headers
		final HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);

		// Returns all events in the current month
		service = getCalendarService();

		java.util.Calendar currentDate = java.util.Calendar.getInstance();
		currentDate.set(java.util.Calendar.DATE, 1);

		// The first day of the month
		DateTime beginningOfMonth = new DateTime(currentDate.getTimeInMillis());
		System.out.println(beginningOfMonth.toString());

		// The last day of the month
		currentDate.roll(java.util.Calendar.MONTH, 1);
		DateTime endOfMonth = new DateTime(currentDate.getTimeInMillis());
		System.out.println(endOfMonth.toString());

		Events events = service.events().list("primary") // Get events from primary calendar...
			.setTimeMin(beginningOfMonth) // Starting at the beginning of the month
			.setTimeMax(endOfMonth) // and ending at the last day of the month
			.execute();

		//get the data from the HttpServletRequest
		List<Event> items = events.getItems();
		if (items.size() == 0) {
			System.out.println("No upcoming events found.");
		}
		else {
			System.out.println("Upcoming events");
			for (Event event : items) {
				DateTime start = event.getStart().getDateTime();
				if (start == null) {
					start = event.getStart().getDate();
				}
				System.out.printf("%s: ==> (%s)\n", event.getSummary(), DateTime.parseRfc3339(start.toStringRfc3339()).toString());
			}
		}

		return new ResponseEntity<String>(events.toPrettyString(), httpHeaders, HttpStatus.OK);

	}

	@RequestMapping(value = "/me/find")
	public ResponseEntity<String> findUser(Principal principal) throws Exception {
		String username = principal.getName();
		String token = oauth2ClientContext.getAccessToken().toString();
		db.accessDB();

		Table table = db.setup.getUsersTable();

		try { // If user exists, send calendar data.
			GetItemSpec spec = new GetItemSpec()
				.withPrimaryKey("userID", username);
			table.getItem(spec);

			return events();
		} catch (Exception e) { // If not, add them to the DB
			table.putItem(new Item()
				.withPrimaryKey("userID", username)
			);

			return new ResponseEntity<String>("New user created", HttpStatus.OK);
		}
	}

	// Display the information for a single event in the user's calendar
	@RequestMapping(value = "/event/detail")
	public ResponseEntity<String> eventDetails(String calID, String eventID) throws Exception {
		db.accessDB();
		final HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);

		service = getCalendarService();

		Event event = service.events().get(calID, eventID).execute();

		return new ResponseEntity<String>(event.toPrettyString(), HttpStatus.OK);
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
		System.out.println("FILTER BEING MADE");
		registration.setFilter(filter);
		registration.setOrder(-100);
		return registration;
	}

	@Bean
	@ConfigurationProperties("google")
	public ClientResources google() {
		System.out.println("RESOURCE BEING MADE");
		return new ClientResources();
	}

	private Filter ssoFilter() {
		CompositeFilter filter = new CompositeFilter();
		List<Filter> filters = new ArrayList<>();
		System.out.println("FILTER LIST MADE");
		String path = "/login/google";
		filters.add(ssoFilter(google(), path));
		filter.setFilters(filters);
		System.out.println("RESOURCE BEING MADE");
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
class ClientResourcesTest {

	@NestedConfigurationProperty
	private AuthorizationCodeResourceDetails client = new AuthorizationCodeResourceDetails();

	@NestedConfigurationProperty
	private ResourceServerProperties resource = new ResourceServerProperties();


	public ClientResourcesTest() {
		client.setClientId(System.getenv("GOOGLE_CLIENT_ID_TEST"));
		client.setClientSecret(System.getenv("GOOGLE_CLIENT_SECRET_TEST"));
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

