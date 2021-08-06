@addToCart
Feature: As a user 
	I want to add product from lister grid, list, pdp and order template page

Background: 
	Given I navigate to storefront page
	And I login to storefront
	
	
Scenario: Add the product to cart from search lister grid page
	And I search for the product with text "*"
	And I get the list of products
	And I change the product view as grid view
	And I add product "addToCart.sku" from grid view to shopping cart
	Then I verify product details on shopping cart page
	|	addToCart.sku	|	orderTemplate.grid.sku.price	|	orderTemplate.grid.sku.quantity	|
	And I logout from the storefront