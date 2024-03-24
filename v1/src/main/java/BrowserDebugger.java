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
		AddToCartButton.click();
		Thread.sleep(2000);
		try {
			//js.executeScript("arguments[0].click();", NoThanksButton);
			act.moveToElement(NoThanksButton).click().build().perform();
		}
		catch(Exception e) {
			System.out.println("Pop up not found");
		}
		Thread.sleep(2000);

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
