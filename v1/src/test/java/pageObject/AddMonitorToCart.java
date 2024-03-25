package pageObject;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddMonitorToCart {

	WebDriver ldriver;
	WebDriverWait wait;
	public String productName;
	public String productNameOneOutofTwo;
	public String productNameTwoOutofTwo;
	public Boolean check;
	
	
	@FindBy (xpath="//input[contains(@id,'searchtextbox')]")
	public WebElement SearchBox;
	
	@FindBy (xpath="//div[contains (@data-component-type,'s-search-result')]//h2//child::a//child::span")
	public List<WebElement> ItemList;

	@FindBy (xpath="//input[contains(@id,'add-to-cart') and (@formaction)]")
	public WebElement AddToCartButton;
	
	@FindBy (xpath="//span[contains(@id,'cart-count')]")
	public WebElement CartItemCount;
	
	@FindBy (xpath="//a[contains(@href,'cart/view')]")
	public WebElement CartIcon;
	
	@FindBy (xpath="//div[@id='corePrice_feature_div']//span[contains (@class,'whole')]")
	public WebElement ProductPagePriceWholeNumber;
	
	@FindBy (xpath="//div[@id='corePrice_feature_div']//span[contains (@class,'fraction')]")
	public WebElement ProductPagePriceFractionNumber;
	
	@FindBy (xpath="//span[contains(@class,'product-price')]")
	public WebElement ProductPriceInsideCart;
	
	@FindBy (xpath="//span[contains(@id,'subtotal')]//span")
	public WebElement ProductSubtotal;
	
	@FindBy (xpath="//a[contains(@id,'location')]")
	public WebElement Location;
	
	@FindBy (xpath="//input[contains(@id,'ZipUpdateInput')]")
	public WebElement ZipInput;
	
	@FindBy (xpath="//button[contains(@id,'announce')]")
	public WebElement DoneButton;
	
	@FindBy (xpath="(//input[contains(@id,'GLUXConfirmClose')])[2]")
	public WebElement ContinueButton;
	
	@FindBy (xpath="//span[contains(text(),'hanks')]")
	public WebElement NoThanksButton;
	
	@FindBy (xpath="(//button[@aria-label='Close'])[2]")
	public WebElement CloseButton;
	
	@FindBy (xpath="//div[@id='glow-ingress-block']//span[2]")
	public WebElement LocationCheck;
	
	@FindBy (xpath="//h1[contains(text(),'Shopping Cart')]")
	public WebElement ShoppingCartText;
	
	public AddMonitorToCart(WebDriver rdriver) {
		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
		wait = new WebDriverWait(rdriver, Duration.ofSeconds(60));
	}
	public void setLocation(String zipCode) throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(Location)).click();
		wait.until(ExpectedConditions.elementToBeClickable(ZipInput)).sendKeys(zipCode);
		ZipInput.sendKeys(Keys.ENTER);
		wait.until(ExpectedConditions.elementToBeClickable(ContinueButton)).click();
		Thread.sleep(2000);
	}
	public void locationCheck(String areaName) {
		if(LocationCheck.getText().contains(areaName)) {
			System.out.println("Location set "+areaName);
		}
		else {
			System.out.println("Test failed");
		}
	}
	public void searchForMonitor(String producttype) {
		wait.until(ExpectedConditions.elementToBeClickable(SearchBox)).click();
		SearchBox.sendKeys(producttype);
		SearchBox.sendKeys(Keys.ENTER);
	}
	public void waitForListOfProduct() {
		wait.until(ExpectedConditions.visibilityOfAllElements(ItemList));
	}
	public void selectFirstMonitor() {
		WebElement firstMonitor = ItemList.get(0);
		productName = ItemList.get(0).getText();
		firstMonitor.click();
	}
	public String displayFirstProductName() {
		//System.out.println("First Product name is : "+productName);
		return productName;
	}
	public String displayOneOutofTWoProductName() {
		productNameOneOutofTwo = productName;
		//System.out.println("Product name is : "+productNameOneOutofTwo);
		return productNameOneOutofTwo;
	}
	public String displayTwoOutofTwoProductName() {
		productNameTwoOutofTwo = productName;
		//System.out.println("Product name is : "+productNameTwoOutofTwo);
		return productNameTwoOutofTwo;
	}
	public String getPriceOfProduct() {
		wait.until(ExpectedConditions.visibilityOf(ProductPagePriceWholeNumber));
		String prodPrice = ProductPagePriceWholeNumber.getText().concat(".").concat(ProductPagePriceFractionNumber.getText());
		//System.out.println("Price of the product on product page is :"+prodPrice);
		return prodPrice;
	}
	public Boolean waitForAddToCartButton() throws InterruptedException {
		Thread.sleep(5000);
		Boolean addToCartButtonCheck = AddToCartButton.isDisplayed();
		return addToCartButtonCheck;		
	}
	public void addToCartButton() throws InterruptedException {
		AddToCartButton.click();
		JavascriptExecutor js = (JavascriptExecutor) ldriver;
		Actions act = new Actions(ldriver);

		Thread.sleep(2000);
		try {
			js.executeScript("arguments[0].click();", NoThanksButton);
		}
		catch(Exception e) {
			//System.out.println("Pop up not found");
		}
		try {
			act.click(NoThanksButton).build().perform();
		}
		catch(Exception e) {
			//System.out.println("Pop up not found");
		}
		try {
			NoThanksButton.click();
		}
		catch(Exception e) {
			//System.out.println("Pop up not found");
		}
		Thread.sleep(2000);
	}
	public int checkIfProductIsAddedToTheCart() {
		int numberOfItemsInsideCart = Integer.parseInt(CartItemCount.getText());
		return numberOfItemsInsideCart;
	}
	public void openCart() throws InterruptedException {
		CartIcon.click();
		Thread.sleep(5000);
	}
	public Boolean checkIfShoppingCartIsOpened() {
		Boolean checkShoppingCartText = ShoppingCartText.isDisplayed();
		return checkShoppingCartText;
	}
	public String getPriceInsideCart() {
		String prodPriceWithCurrency = ProductPriceInsideCart.getText();
		String prodPrice = prodPriceWithCurrency.replace("$", "");
		return prodPrice;
	}
	public String getSubTotal() {
		String prodSubTotalPriceWithCurrency = ProductSubtotal.getText();
		String prodSubTotal = prodSubTotalPriceWithCurrency.replace("$", "");
		return prodSubTotal;
	}
	public Boolean compareTotal(String price1, String price2) {
		if(price1.equals(price2)) {
			check = true;
		}
		else {
			System.out.println("Prices are not matching");
			check = false;
		}
		return check;
	}
	public String checkPriceInsideCartWithString(String productName) {
		String productPriceInCart = ldriver.findElement(By.xpath("//span[contains(text(),'"+productName.trim()+"')]//..//..//..//..//..//..//span[contains(@class,'product-price')]")).getText().replace("$","");
		return productPriceInCart;
	}
	public Double additionOfDoubles(Double db1, Double db2) {
		Double totalOfProductsByAddition = db1 + db2;
		return totalOfProductsByAddition;
	}
}
