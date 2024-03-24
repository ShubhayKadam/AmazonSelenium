package com.testng.AmazonWebpage.v1;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class CommonFunctions extends Amazon_Xpaths{
	WebDriver driver;
	public String getSubTotal() {
		String prodSubTotalPriceWithCurrency = ProductSubtotal.getText();
		String prodSubTotal = prodSubTotalPriceWithCurrency.replace("$", "");
		return prodSubTotal;
	}
	public Double getSubTotalAndConvertToDouble() {
//		Double productsSubTotal = Double.parseDouble((ProductSubtotal).getText().replace("$", ""));
		String productsSubTotalWithCurrency = ProductSubtotal.getText();
		String productsSubTotal = productsSubTotalWithCurrency.replace("$", "");
		Double toDouble = Double.parseDouble(productsSubTotal);
		return toDouble;
	}
	public String getProdPriceFromCart() {
		String prodPriceWithCurrency = ProductPriceInsideCart.getText();
		String prodPrice = prodPriceWithCurrency.replace("$", "");
		return prodPrice;
	}
	public void checkNumberOfItemsInCart(int requiredNumOfItems) {
		int numberOfItemsInsideCart = Integer.parseInt(CartItemCount.getText());
		for(int i=0;i<3;i++) {
			try {
				if(numberOfItemsInsideCart == requiredNumOfItems) {
					CartIcon.click();
					break;
				}
				else {
					driver.navigate().refresh();
				}
			}
			catch(Exception e){
				System.out.println("Cart details missing");
			}
		}
	}

}
