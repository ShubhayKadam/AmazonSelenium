package com.testng.AmazonWebpage.v1;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


public class AmazonPage extends Amazon_Xpaths{
	WebDriver driver;
	WebDriverWait wait;
	public AmazonPage(WebDriver driver) {
		this.driver=driver;
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, Duration.ofSeconds(60));
	}
	public void addMonitor() throws InterruptedException {
		System.out.println("Inside addMonitor() method");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Actions act = new Actions(driver);
		CommonFunctions cf = PageFactory.initElements(driver, CommonFunctions.class);
		
		wait.until(ExpectedConditions.elementToBeClickable(Location)).click();
		wait.until(ExpectedConditions.elementToBeClickable(ZipInput)).sendKeys("10001");
		ZipInput.sendKeys(Keys.ENTER);
		wait.until(ExpectedConditions.elementToBeClickable(ContinueButton)).click();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(SearchBox)).click();
		SearchBox.sendKeys("Monitor");
		SearchBox.sendKeys(Keys.ENTER);
		wait.until(ExpectedConditions.visibilityOfAllElements(ItemList));
		System.out.println("First monitor name is : "+ItemList.get(0).getText());
		WebElement firstMonitor = ItemList.get(0);
		firstMonitor.click();

		wait.until(ExpectedConditions.visibilityOf(ProductPagePriceWholeNumber));
		String prodPrice = ProductPagePriceWholeNumber.getText().concat(".").concat(ProductPagePriceFractionNumber.getText());
		System.out.println("Price of the product on product page is :"+prodPrice);

		wait.until(ExpectedConditions.elementToBeClickable(AddToCartButton));
		AddToCartButton.click();
		Thread.sleep(2000);
		try {
			act.moveToElement(NoThanksButton).click().build().perform();
		}
		catch(Exception e) {
			System.out.println("Pop up not found");
		}
		try {
			js.executeScript("arguments[0].click();", NoThanksButton);
		}
		catch(Exception e) {
			System.out.println("Pop up not found");
		}
		Thread.sleep(2000);
		System.out.println("Cart has "+CartItemCount.getText()+" Items");
		cf.checkNumberOfItemsInCart(1);
		wait.until(ExpectedConditions.visibilityOf(ProductPriceInsideCart));
		String prodPriceInCart = cf.getProdPriceFromCart();
		Assert.assertEquals(prodPriceInCart, prodPrice);
		System.out.println("Prices from product page "+prodPrice+" and cart page "+prodPriceInCart+" are matching");
		
		String prodSubTotalPrice = cf.getSubTotal();
		Assert.assertEquals(prodSubTotalPrice, prodPrice);
		System.out.println("Prices from product page "+prodPrice+" and sub total in the cart "+prodSubTotalPrice+" are matching");
		System.out.println("End of addMonitor()");
	}
	public void addLaptop() throws InterruptedException {
		System.out.println("Inside addLaptop() method");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Actions act = new Actions(driver);
		CommonFunctions cf = PageFactory.initElements(driver, CommonFunctions.class);
		
		wait.until(ExpectedConditions.elementToBeClickable(Location)).click();
		wait.until(ExpectedConditions.elementToBeClickable(ZipInput)).sendKeys("10001");
		ZipInput.sendKeys(Keys.ENTER);
		wait.until(ExpectedConditions.elementToBeClickable(ContinueButton)).click();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(SearchBox)).click();
		SearchBox.sendKeys("Laptop");
		SearchBox.sendKeys(Keys.ENTER);
		wait.until(ExpectedConditions.visibilityOfAllElements(ItemList));
		System.out.println("Second laptop name is : "+ItemList.get(1).getText());
		WebElement secondLaptop = ItemList.get(1);
		secondLaptop.click();

		wait.until(ExpectedConditions.visibilityOf(ProductPagePriceWholeNumber));
		String prodPrice = ProductPagePriceWholeNumber.getText().concat(".").concat(ProductPagePriceFractionNumber.getText());
		System.out.println("Price of the product on product page is :"+prodPrice);

		wait.until(ExpectedConditions.elementToBeClickable(AddToCartButton));
		AddToCartButton.click();
		try {
			act.moveToElement(NoThanksButton).click().build().perform();
		}
		catch(Exception e) {
			System.out.println("Pop up not found");
		}
		try {
			js.executeScript("arguments[0].click();", NoThanksButton);
		}
		catch(Exception e) {
			System.out.println("Pop up not found");
		}
		Thread.sleep(5000);
		System.out.println("Cart has "+CartItemCount.getText()+" Items");
		cf.checkNumberOfItemsInCart(1);
		wait.until(ExpectedConditions.visibilityOf(ProductPriceInsideCart));
		String prodPriceInCart = cf.getProdPriceFromCart();
		Assert.assertEquals(prodPriceInCart, prodPrice);
		System.out.println("Prices from product page "+prodPrice+" and cart page "+prodPriceInCart+" are matching");
		
		String prodSubTotalPrice = cf.getSubTotal();
		Assert.assertEquals(prodSubTotalPrice, prodPrice);
		System.out.println("Prices from product page "+prodPrice+" and sub total in the cart "+prodSubTotalPrice+" are matching");
		System.out.println("End of addLaptop() method");
	}
	public void addHeadphonePlusKB() throws InterruptedException {
		System.out.println("Inside addHeadphonePlusKB() method");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Actions act = new Actions(driver);
		CommonFunctions cf = PageFactory.initElements(driver, CommonFunctions.class);
		String headphoneAdded=null;
		String prodPriceHeadphone=null;
		
		wait.until(ExpectedConditions.elementToBeClickable(Location)).click();
		wait.until(ExpectedConditions.elementToBeClickable(ZipInput)).sendKeys("10001");
		ZipInput.sendKeys(Keys.ENTER);
		wait.until(ExpectedConditions.elementToBeClickable(ContinueButton)).click();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(SearchBox)).click();
		SearchBox.sendKeys("Headphones");
		SearchBox.sendKeys(Keys.ENTER);
		wait.until(ExpectedConditions.visibilityOfAllElements(ItemList));
		for(int j=0;j<ItemList.size();j++) {
			WebElement headphone = ItemList.get(j);
			headphoneAdded = ItemList.get(j).getText().trim();
			headphone.click();
			js.executeScript("window.scrollBy(0,200)");
			try {
				prodPriceHeadphone = ProductPagePriceWholeNumber.getText().concat(".").concat(ProductPagePriceFractionNumber.getText());
				System.out.println("First headphone name is : "+headphoneAdded);
				System.out.println("Price of headphone on product page is :"+prodPriceHeadphone);
				AddToCartButton.click();
				System.out.println("Item added to the cart");
				Thread.sleep(2000);
				CloseButton.click();
			}
			catch(Exception e) {
				System.out.println("Add to cart is not displayed or close button not displayed");
			}
			int numberOfItemsInsideCart = Integer.parseInt(CartItemCount.getText());
			if(numberOfItemsInsideCart == 1) {
				break;
			}
			else{
				driver.navigate().back();
			}
			
		}

		wait.until(ExpectedConditions.elementToBeClickable(SearchBox)).click();
		SearchBox.clear();
		SearchBox.sendKeys("Keyboard");
		SearchBox.sendKeys(Keys.ENTER);
		wait.until(ExpectedConditions.visibilityOfAllElements(ItemList));
		String keyboardAdded = ItemList.get(0).getText().trim();
		System.out.println("First Keyboard name is : "+keyboardAdded);
		WebElement firstKeyboard = ItemList.get(0);
		firstKeyboard.click();

		wait.until(ExpectedConditions.visibilityOf(ProductPagePriceWholeNumber));
		String prodPriceKeyboard = ProductPagePriceWholeNumber.getText().concat(".").concat(ProductPagePriceFractionNumber.getText());
		System.out.println("Price of the product on product page is :"+prodPriceKeyboard);

		wait.until(ExpectedConditions.elementToBeClickable(AddToCartButton));
		AddToCartButton.click();
		try {
			act.moveToElement(NoThanksButton).click().build().perform();
		}
		catch(Exception e) {
			System.out.println("Pop up not found");
		}
		try {
			js.executeScript("arguments[0].click();", NoThanksButton);
		}
		catch(Exception e) {
			System.out.println("Pop up not found");
		}
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(CartItemCount));
		System.out.println("Cart has "+CartItemCount.getText()+" Items");
		cf.checkNumberOfItemsInCart(2);
		Thread.sleep(2000);
		String keyboardPriceInCart = driver.findElement(By.xpath("//span[contains(text(),'"+keyboardAdded+"')]//..//..//..//..//..//..//span[contains(@class,'product-price')]")).getText().replace("$","");
		String headphonePriceInCart = driver.findElement(By.xpath("//span[contains(text(),'"+headphoneAdded+"')]//..//..//..//..//..//..//span[contains(@class,'product-price')]")).getText().replace("$","");
		
		System.out.println(headphonePriceInCart+" and "+keyboardPriceInCart);
		Assert.assertEquals(prodPriceKeyboard, keyboardPriceInCart);
		System.out.println("Prices from product page "+prodPriceKeyboard+" and cart page "+keyboardPriceInCart+" are matching");
		Assert.assertEquals(prodPriceHeadphone, headphonePriceInCart);
		System.out.println("Prices from product page "+prodPriceHeadphone+" and cart page "+headphonePriceInCart+" are matching");

		Double productsSubTotal = cf.getSubTotalAndConvertToDouble();
		Double kbPriceInCart = Double.parseDouble(keyboardPriceInCart);
		Double hpPriceInCart = Double.parseDouble(headphonePriceInCart);
		Double totalOfProductsByAddition = kbPriceInCart+hpPriceInCart;
		Assert.assertEquals(totalOfProductsByAddition, productsSubTotal);
		System.out.println("Additon of product prices "+totalOfProductsByAddition+" and sub total in the cart "+productsSubTotal+" are matching");
		System.out.println("End of method addHeadphonePlusKB()");
	}
}
