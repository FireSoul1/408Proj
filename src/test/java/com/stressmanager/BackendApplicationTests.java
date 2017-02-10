package com.stressmanager;

//JUnit
import org.junit.runner.RunWith;
//Spring
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import org.springframework.boot.test.web.client.TestRestTemplate;
//TestNG
import org.testng.Assert;
import org.junit.Test;



@RunWith(SpringRunner.class)
@SpringBootTest
public class BackendApplicationTests extends AbstractTestNGSpringContextTests {
    @Autowired
    private BackendApplication controller;
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
	public void homeNotNull() throws Exception{
        System.out.println("\nRunnning test case 1: Checking that maincontroller is working.");
        Assert.assertNotNull(controller);
	}
    @Test
	public void checkHomePage() throws Exception{
        System.out.println("\nRunnning test case 2: Checking that HomePage loads up.");
        

	}
    @Test
	public void DBSetUpLocal() throws Exception{
        System.out.println("\nRunnning test case 2: Checking that the Local DB is setup.");

	}


}
