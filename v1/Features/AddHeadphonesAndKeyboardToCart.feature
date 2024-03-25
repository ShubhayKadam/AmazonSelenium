@TwoProducts
Feature: AddHeadphoneAndKeyboardtoCart

Scenario: Successful addition of the first headphone and first keyboard inside cart with price matching with product, cart sub total and product price inside cart
Given User launch chrome browser
When User opens "https://www.amazon.com"
Then Window with title "Amazon.com. Spend less. Smile more." should be opened
When User Sets the location zip to "10001"
Then Location should be updated to "New York"
When User search for "Headphones" and hits Enter
Then List of Headphones available should be displayed
When User selects "1" st/nd/rd result from the list of results
Then First Headphones information page should be displayed
When User should be able to get price of the first product
Then Add to cart button should be displayed
When User clicks on Add to cart button
Then Product should get added in the cart and Cart item count should get updated to 1
When User search for "Keyboard" and hits Enter
Then List of Keyboards available should be displayed
When User selects "1" st/nd/rd result from the list of results
Then First Keyboard information page should be displayed
When User should be able to get price of the second product
Then Add to cart button should be displayed
When User clicks on Add to cart button
Then Product should get added in the cart and Cart item count should get updated to 2
When User clicks on cart icon on top right
Then Cart should get opened
When User checks price of the 1st product "Headphones" inside cart
Then Cart Price should match with price of Headphones from the product from product page
When User checks price of the 2nd product "Keyboard" inside cart
Then Cart Price should match with price of Keyboard from the product from product page
When User checks sub total of the cart
Then Sub Total Price should match with total price of the two products added
Then Close the browser