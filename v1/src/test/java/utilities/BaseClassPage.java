package utilities;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;


public class BaseClassPage {

	public WebDriver driver;
	@BeforeMethod
	public void setup() {
		System.out.println("This is @BeforeMethod");
		driver = BrowserFactory.startApp(driver);
		System.out.println("Browser Launched");
	}
	@AfterMethod
	public void teardownclass() {
		System.out.println("This is @AfterMethod");
		BrowserFactory.quitBrowser(driver);
	}
}
