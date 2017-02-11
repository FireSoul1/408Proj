package com.stressmanager;

//JUnit
import org.junit.runner.RunWith;
import org.junit.Test;
import org.junit.Before;
//Spring
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

//selenium
// import org.openqa.selenium.WebDriver;
// import org.openqa.selenium.firefox.FirefoxDriver;
//TestNG
import org.testng.Assert;




@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest
public class BackendApplicationTests extends AbstractTestNGSpringContextTests {
    @Autowired
    BackendApplication controller;
//    @Autowired
//    TestRestTemplate restTemplate;

    @Before
    public void setup() {
        System.out.println("TEST ARE ACTUALLY RUNNING!!");
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
