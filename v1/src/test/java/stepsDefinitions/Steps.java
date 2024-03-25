package stepsDefinitions;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObject.AddMonitorToCart;

public class Steps {
	public WebDriver driver;
	public AddMonitorToCart mtr;
	String priceOnProdPage;
	public String priceOfProductInsidecart;
	public String priceOfFirstProductInsidecart;
	public String priceOfSecondProductInsidecart;
	public String subTotalInsideCart;
	public String oneOutofTwoProduct;
	public String twoOutofTwoProduct;
	public String priceOnProdPageOfProdOneofTwo;
	public String priceOnProdPageOfProdTwofTwo;

	@Given("User launch chrome browser")
	public void user_launch_chrome_browser() {
		System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"//Drivers//chromedriver.exe");
		
		driver = new ChromeDriver();
		mtr = new AddMonitorToCart(driver);
		driver.manage().window().maximize();
	}

	@When("User opens {string}")
	public void user_opens(String url) throws InterruptedException {
		driver.get(url);
		//Thread.sleep(10000);
	}

	@Then("Window with title {string} should be opened")
	public void window_with_title_should_be_opened(String string) {
		String title = driver.getTitle();
		Assert.assertEquals(title, "Amazon.com. Spend less. Smile more.");
		System.out.println(title+"Page is opened");
	}

	@When("User Sets the location zip to {string}")
	public void user_sets_the_location_zip_to(String zipCode) throws InterruptedException {
		mtr.setLocation(zipCode);
	}

	@Then("Location should be updated to {string}")
	public void location_should_be_updated_to(String location) {
		mtr.locationCheck(location);
		System.out.println("Location has been updated to "+location);
	}

	@When("User search for {string} and hits Enter")
	public void user_search_for_and_hits_enter(String producttype) {
		mtr.searchForMonitor(producttype);
	}

	@Then("List of Monitor available should be displayed")
	public void list_of_monitor_available_should_be_displayed() {
		mtr.waitForListOfProduct();  
		System.out.println("Monitor list has displayed");
	}
	
	@Then("List of Laptop available should be displayed")
	public void list_of_laptop_available_should_be_displayed() {
		mtr.waitForListOfProduct(); 
		System.out.println("Laptop list has displayed");
	}

	/*
	 * @When("User selects first result from the list of results") public void
	 * user_selects_first_result_from_the_list_of_results() {
	 * mtr.selectFirstMonitor(); }
	 */
	
	@When("User selects {string} st\\/nd\\/rd result from the list of results")
	public void user_selects_st_nd_rd_result_from_the_list_of_results(String cnt) {
		int intCnt = Integer.parseInt(cnt);
		mtr.selectNthProduct(intCnt);
	}

	@Then("First monitors information page should be displayed")
	public void first_monitors_information_page_should_be_displayed() {
		if(!mtr.displayFirstProductName().isEmpty()) {
			System.out.println("Monitor is displayed");
		}
		else {
			System.out.println("Test Case failed as Monitor name is not displayed");
		}
	}

	@When("User should be able to get price of the product")
	public void user_should_be_able_to_get_price_of_the_product() {
		priceOnProdPage = mtr.getPriceOfProduct();
	}
	
	@When("User should be able to get price of the first product")
	public void user_should_be_able_to_get_price_of_the_first_product() {
		priceOnProdPageOfProdOneofTwo = mtr.getPriceOfProduct();
	}

	@When("User should be able to get price of the second product")
	public void user_should_be_able_to_get_price_of_the_second_product() {
		priceOnProdPageOfProdTwofTwo = mtr.getPriceOfProduct();
	}

	@Then("Add to cart button should be displayed")
	public void add_to_cart_button_should_be_displayed() throws InterruptedException {
		if(mtr.waitForAddToCartButton()==true) {
			System.out.println("Add to cart button is displayed");
		}
		else {
			System.out.println("Test failed as Add to cart button has not displayed");
			Assert.assertTrue(false);
		}
	}

	@When("User clicks on Add to cart button")
	public void user_clicks_on_add_to_cart_button() throws InterruptedException {
		mtr.addToCartButton();
	}

	@Then("Product should get added in the cart and Cart item count should get updated to {int}")
	public void product_should_get_added_in_the_cart_and_cart_item_count_should_get_updated_to(Integer int1) {
		if(mtr.checkIfProductIsAddedToTheCart()==int1) {
			System.out.println("One product has been added to the cart");
		}
		else {
			System.out.println("Test failed as item in the cart is not equal to given number");
			Assert.assertTrue(false);
		}
	}

	@When("User clicks on cart icon on top right")
	public void user_clicks_on_cart_icon_on_top_right() throws InterruptedException {
		mtr.openCart();
	}

	@Then("Cart should get opened")
	public void cart_should_get_opened() {
		if(mtr.checkIfShoppingCartIsOpened()==true) {
			System.out.println("Shopping cart has been opened");
		}
		else {
			System.out.println("Test failed as shopping cart has not been displayed");
			Assert.assertTrue(false);
		}
	}

	@When("User checks price of the monitor inside cart")
	public void user_checks_price_of_the_monitor_inside_cart() {
		priceOfProductInsidecart = mtr.getPriceInsideCart();
	}

	@Then("Cart Price should match with price of the product from product page")
	public void cart_price_should_match_with_price_of_the_product_from_product_page() {
		if(mtr.compareTotal(priceOnProdPage, priceOfProductInsidecart)==true) {
			System.out.println(priceOnProdPage+" and "+priceOfProductInsidecart+" are matching");
		}
		else {
			System.out.println("Test failed as product price is not matching with cart");
			System.out.println(priceOnProdPage+" and "+priceOfProductInsidecart+" are NOT matching");
			Assert.assertTrue(false);	
		}
	}
	@Then("Cart Price should match with price of Headphones from the product from product page")
	public void cart_price_should_match_with_price_of_headphones_from_the_product_from_product_page() {
		if(mtr.compareTotal(priceOnProdPageOfProdOneofTwo, priceOfFirstProductInsidecart)==true) {
			System.out.println(priceOnProdPageOfProdOneofTwo+" and "+priceOfFirstProductInsidecart+" are matching");
		}
		else {
			System.out.println("Test failed as product price is not matching with cart");
			System.out.println(priceOnProdPageOfProdOneofTwo+" and "+priceOfFirstProductInsidecart+" are NOT matching");
			Assert.assertTrue(false);
		}
	}

	@Then("Cart Price should match with price of Keyboard from the product from product page")
	public void cart_price_should_match_with_price_of_keyboard_from_the_product_from_product_page() {
		if(mtr.compareTotal(priceOnProdPageOfProdTwofTwo, priceOfSecondProductInsidecart)==true) {
			System.out.println(priceOnProdPageOfProdTwofTwo+" and "+priceOfSecondProductInsidecart+" are matching");
		}
		else {
			System.out.println("Test failed as product price is not matching with cart");
			System.out.println(priceOnProdPageOfProdTwofTwo+" and "+priceOfSecondProductInsidecart+" are NOT matching");
			Assert.assertTrue(false);
		}
	}

	@When("User checks sub total of the cart")
	public void user_checks_sub_total_of_the_cart() {
		subTotalInsideCart = mtr.getSubTotal();
	}

	@Then("Sub Total Price should match with price of the product from product page")
	public void sub_total_price_should_match_with_price_of_the_product_from_product_page() {
		if(mtr.compareTotal(priceOnProdPage, subTotalInsideCart)==true) {
			System.out.println(priceOnProdPage+" and "+subTotalInsideCart+" are matching");
		}
		else {
			System.out.println("Test failed as product price is not matching with sub total");
			Assert.assertTrue(false);
		}
	}
	@Then("List of Headphones available should be displayed")
	public void list_of_headphones_available_should_be_displayed() {
		mtr.waitForListOfProduct();
	}

	@Then("First Headphones information page should be displayed")
	public void first_headphones_information_page_should_be_displayed() {
		oneOutofTwoProduct = mtr.displayOneOutofTWoProductName();
		if(!mtr.displayOneOutofTWoProductName().isEmpty()) {
			System.out.println("Product is displayed");
		}
		else {
			System.out.println("Test Case failed as Product name is not displayed");
			Assert.assertTrue(false);
		}
	}

	@Then("List of Keyboards available should be displayed")
	public void list_of_keyboards_available_should_be_displayed() {
		mtr.waitForListOfProduct();
	}

	@Then("First Keyboard information page should be displayed")
	public void first_keyboard_information_page_should_be_displayed() {
		twoOutofTwoProduct = mtr.displayTwoOutofTwoProductName();
		if(!mtr.displayTwoOutofTwoProductName().isEmpty()) {
			System.out.println("Product is displayed");
		}
		else {
			System.out.println("Test Case failed as Product name is not displayed");
			Assert.assertTrue(false);
		}
	}
	
	@When("User checks price of the 1st product {string} inside cart")
	public void user_checks_price_of_the_1st_product_inside_cart(String productName) {
		productName= oneOutofTwoProduct;
		System.out.println("One out of two is :"+productName);
		priceOfFirstProductInsidecart = mtr.checkPriceInsideCartWithString(productName);
	}

	@When("User checks price of the 2nd product {string} inside cart")
	public void user_checks_price_of_the_2nd_product_inside_cart(String productName) {
		productName= twoOutofTwoProduct;
		System.out.println("Two out of two is :"+productName);
		priceOfSecondProductInsidecart = mtr.checkPriceInsideCartWithString(productName);
	}

	@Then("Sub Total Price should match with total price of the two products added")
	public void sub_total_price_should_match_with_total_price_of_the_two_products_added() {
	    Double subTotalOfTwoProducts = Double.parseDouble(mtr.getSubTotal());
	    //System.out.println(subTotalOfTwoProducts);
	    Double firstProdPriceToDbl = Double.parseDouble(priceOfFirstProductInsidecart);
	    //System.out.println(firstProdPriceToDbl);
	    Double secondProdPriceToDbl = Double.parseDouble(priceOfSecondProductInsidecart);
	    //System.out.println(secondProdPriceToDbl);
	    Double additionOfTwo = mtr.additionOfDoubles(firstProdPriceToDbl, secondProdPriceToDbl);
	    //System.out.println(additionOfTwo);
	    if(Double.compare(subTotalOfTwoProducts, additionOfTwo)==0) {
	    	System.out.println("Products total is matching with addition");
	    }
	    else {
	    	System.out.println("Test failed as product total and subtotal is not matching");
	    	Assert.assertTrue(false);
	    }
	}

	@Then("Close the browser")
	public void close_the_browser() {
		driver.close();
	}

}
