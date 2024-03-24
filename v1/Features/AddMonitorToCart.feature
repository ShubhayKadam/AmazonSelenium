Feature: AddMonitorToCart

Scenario: Successful addition of the first monitor inside cart with price matching with product, cart sub total and product price inside cart
Given User launch chrome browser
When User opens "https://www.amazon.com"
Then Window with title "Amazon.com. Spend less. Smile more." should be opened
When User Sets the location zip to "10001"
Then Location should be updated to "New York"
When User search for Monitor and hits Enter
Then List of monitors available should be displayed
When User selects first result from the list of results
Then First monitors information page should be displayed
When User should be able to get price of the product
Then Add to cart button should be displayed
When User clicks on Add to cart button
Then Product should get added in the cart and Cart item count should get updated to 1
When User clicks on cart icon on top right
Then Cart should get opened
When User checks price of the monitor inside cart
Then Cart Price should match with price of the product from product page
When User checks sub total of the cart
Then Sub Total Price should match with price of the product from product page
Then Close the browser