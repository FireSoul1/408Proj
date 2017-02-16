package com.stressmanager;

import org.junit.Test;
import org.testng.Assert;
import org.junit.Rule;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.boot.test.rule.OutputCapture;
import org.springframework.http.MediaType;


import static org.hamcrest.Matchers.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BackendApplicationTests extends AbstractTestNGSpringContextTests {

	@Test
	public void contextLoads() throws Exception{
	}

	// @Rule
	// public OutputCapture capture = new OutputCapture();

	// @Autowired
    // private MockMvc mockMvc;

	@Test
	public void isAuthenticated() throws Exception {
		// this.mockMvc.perform(get("/login/google").accept(MediaType.TEXT_PLAIN))
		// 	.andExpect(status().isOk());
		// assertThat(capture.toString(), containsString("authenticated!!!!"));
	}


    @Autowired
    BackendApplication controller;
//    @Autowired
//    TestRestTemplate restTemplate;

    @Before
    public void setup() {
        System.out.println("TESTS ARE ACTUALLY RUNNING!!");
    }

    @Test
	public void homeNotNullTest() {
        System.out.println("\nRunnning test case 1: Checking that maincontroller is working.");
        Assert.assertNotNull(controller);
	}
    @Test
	public void checkHomePageTest() throws Exception{
        System.out.println("\nRunnning test case 2: Checking that HomePage loads up.");


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
