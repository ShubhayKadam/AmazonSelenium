package utilities;

import java.util.Arrays;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class BrowserFactory {
	public static WebDriver startApp(WebDriver driver) {
		System.setProperty("webdriver.chrome.driver","C:\\Users\\Shubhay\\git\\AmazonSelenium1\\v1\\Drivers\\chromedriver.exes");
		driver = new ChromeDriver();
		driver.get("https://www.amazon.com");
		driver.manage().window().maximize();
		System.out.println("Navigated to the URL: "+driver.getTitle());
		return driver;
	}
	public static void quitBrowser(WebDriver driver) {
		driver.close();
		System.out.println("Browser closed");
	}

}
