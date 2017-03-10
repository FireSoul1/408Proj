import org.seleniumhq.selenium.*;
import org.seleniumhq.selenium.WebDriver;
import org.seleniumhq.selenium.remote.DesiredCapabilities;
import org.seleniumhq.selenium.remote.RemoteWebDriver;

public class StressFormPageTest {
	@Test
	public static void main(String[] args) throws Exception {
 
    DesiredCapabilities caps = DesiredCapabilities.chrome();
    caps.setCapability("platform", "Mac");
    caps.setCapability("version", "43.0");
 
    WebDriver driver = new RemoteWebDriver("http://localhost:9515", DesiredCapabilities.chrome());
 	  System.out.println("\nmeowmeowmeow\n");
 	  System.out.println("arf");
    /**
     * Goes to Sauce Lab's guinea-pig page and prints title
     */
 
    driver.get("https://saucelabs.com/test/guinea-pig");
    System.out.println("title of page is: " + driver.getTitle());
 
    driver.quit();
  }
}