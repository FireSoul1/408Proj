package com.stressmanager;
//Selenium
import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
//JUnit and Spring
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.test.context.*;
import org.junit.runner.RunWith;
import org.junit.Test;
//Java
import java.net.*;



@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@ContextConfiguration
public class StressFormPageTest{

	@LocalServerPort
	private int port;

	@Test
	public void testThis() throws MalformedURLException{
		DesiredCapabilities caps = DesiredCapabilities.chrome();
	    caps.setCapability("platform", "Mac");
	    caps.setCapability("version", "43.0");

		WebDriver driver = new RemoteWebDriver("http://localhost:9515", DesiredCapabilities.chrome());
		
		try{
			Thread.sleep(50);
		} catch(Exception e) {
			e.printStackTrace();
		}
	 	  System.out.println(Colors.ANSI_YELLOW+"\nmeowmeowmeow\n");
	 	  System.out.println(Colors.ANSI_RED+"arf"+Colors.ANSI_WHITE);
	    /**
	     * Goes to Sauce Lab's guinea-pig page and prints title
	     */

	    driver.get("https://saucelabs.com/test/guinea-pig");
	    System.out.println("title of page is: " + driver.getTitle());

	    driver.quit();

	}

}
