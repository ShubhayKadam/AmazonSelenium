import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.testng.AmazonWebpage.v1.Amazon_Xpaths;

public class BrowserDebugger extends Amazon_Xpaths{

	@Test
	public void newfunc(WebDriver driver, WebDriverWait wait) throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Actions act = new Actions(driver);
		Boolean addToCartButtonCheck = AddToCartButton.isDisplayed();
		if(addToCartButtonCheck==true) {
			   System.out.println("Add to cart button is displayed");
		   }
		   else {
			   System.out.println("Test failed as Add to cart button has not displayed");
		   }

	}
	@BeforeTest	
	public void beforeTest() throws InterruptedException {
		System.out.println("Inside debugger beforetest");
		System.setProperty("webdriver.chrome.driver","C:\\Users\\Shubhay\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("debuggerAddress", "localhost:9014");
		WebDriver driver = new ChromeDriver (options);
		BrowserDebugger check = 
				PageFactory.initElements(driver, BrowserDebugger.class);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		check.newfunc(driver, wait);
		
	}
}
