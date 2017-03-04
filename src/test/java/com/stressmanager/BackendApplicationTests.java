package com.stressmanager;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.*;

import javax.servlet.Filter;

import org.junit.Test;
import org.testng.Assert;
import org.junit.Rule;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.rule.OutputCapture;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.security.test.context.support.*;
import org.springframework.http.MediaType;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.*;
import static org.hamcrest.Matchers.*;



@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@ContextConfiguration
public class BackendApplicationTests extends AbstractTestNGSpringContextTests {

    @LocalServerPort
	private int port;

    @Autowired
    BackendApplication controller;

    @Autowired
    WebApplicationContext wac;
    @Autowired
    private Filter springSecurityFilterChain;

    MockMvc mvc;

    @Before
    public void setup() {
        System.out.println("TESTS ARE ACTUALLY RUNNING!!");
        mvc = webAppContextSetup(wac)
                .addFilters(springSecurityFilterChain)
                .build();

    }

    @Test
	public void homeNotNullTest() {
        System.out.println("\nRunnning test case 1: Checking that maincontroller is working.");
        Assert.assertNotNull(controller);
	}
    @Test
	public void checkHomePageTest() throws Exception{
        System.out.println("\nRunnning test case 2: Checking that HomePage loads up.");
        this.mvc.perform(get("/"))
                .andExpect(status().isOk());

    }
    @Test
    public void RedirectTest() throws Exception{
        System.out.println("\nRunnning test case 3: Redirect Test.");
        HttpStatus stats = new TestRestTemplate("30354291750-bs651utajfcpf0a5outo8nqnmd8thm86.apps.googleusercontent.com", "8BrM4VbRR5dgYlinrySTKNU5")
            .getForEntity("http://localhost:"+port+"/login/google", String.class).getStatusCode();
        System.out.println("\n\n   The Status:"+stats);

        Assert.assertEquals(
				HttpStatus.FOUND == stats,
                true);


    }
    @Test
    @WithMockUser
    public void PingTest() throws Exception{
        System.out.println("\n\nRunnning test case 4: Ping Test\n");
        // mvc.perform(formLogin("/login/google"))
        //     .andExpect(status().isMovedTemporarily())
        //     .andExpect(redirectedUrl("/"))
        //     .andExpect(authenticated().withUsername("user"));

    }


    @Test
	public void DBSetUpLocalTest() throws Exception{
        System.out.println("\nRunnning test case 3: Checking that the Local DB is setup.");
        DBSetup.localDB();
	}
    @Test
	public void DBSetUpRemoteTest() throws Exception{
        System.out.println("\nRunnning test case 4: Checking that the Remote DB is setup.");
        DBSetup.remoteDB();
	}


}
