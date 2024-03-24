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
	public String subTotalInsideCart;

	@Given("User launch chrome browser")
	public void user_launch_chrome_browser() {
		System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"//Drivers//chromedriver.exe");
		driver = new ChromeDriver();
		mtr = new AddMonitorToCart(driver);
	}

	@When("User opens {string}")
	public void user_opens(String url) {
		driver.get(url);
	}

	@Then("Window with title {string} should be opened")
	public void window_with_title_should_be_opened(String string) {
		String title = driver.getTitle();
		Assert.assertEquals(title, "Amazon.com. Spend less. Smile more.");
	}

	@When("User Sets the location zip to {string}")
	public void user_sets_the_location_zip_to(String zipCode) throws InterruptedException {
		mtr.setLocation(zipCode);
	}

	@Then("Location should be updated to {string}")
	public void location_should_be_updated_to(String location) {
		mtr.locationCheck(location);
	}

	@When("User search for Monitor and hits Enter")
	public void user_search_for_monitor_and_hits_enter() {
			mtr.searchForMonitor();
		}

		@Then("List of monitors available should be displayed")
		public void list_of_monitors_available_should_be_displayed() {
			mtr.waitForListOfMonitor();  
		}

		@When("User selects first result from the list of results")
		public void user_selects_first_result_from_the_list_of_results() {
			mtr.selectFirstMonitor();
		}

		@Then("First monitors information page should be displayed")
		public void first_monitors_information_page_should_be_displayed() {
			if(!mtr.displayFirstMonitorName().isEmpty()) {
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

		@Then("Add to cart button should be displayed")
		public void add_to_cart_button_should_be_displayed() throws InterruptedException {
			if(mtr.waitForAddToCartButton()==true) {
				System.out.println("Add to cart button is displayed");
			}
			else {
				System.out.println("Test failed as Add to cart button has not displayed");
			}
		}

		@When("User clicks on Add to cart button")
		public void user_clicks_on_add_to_cart_button() throws InterruptedException {
			mtr.addToCartButton();
		}

		@Then("Product should get added in the cart and Cart item count should get updated to {int}")
		public void product_should_get_added_in_the_cart_and_cart_item_count_should_get_updated_to(Integer int1) {
			if(mtr.checkIfProductIsAddedToTheCart()==1) {
				System.out.println("One product has been added to the cart");
			}
			else {
				System.out.println("Test failed as item in the cart is not equal to 1");
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
			}
		}

	}
