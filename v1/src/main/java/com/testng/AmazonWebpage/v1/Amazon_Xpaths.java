package com.testng.AmazonWebpage.v1;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Amazon_Xpaths {
	
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
}
