@orderTemplate 
Feature: As a user 
	I want to create order template from lister grid, list, pdp and cart page

Background: 
	Given I navigate to storefront page
	And I login to storefront
	
	
Scenario: Create the order template from search lister grid page
	And I search for the product with text "*"
	And I get the list of products
	And I change the product view as grid view
	When I create the order template for product "orderTemplate.grid.sku"
	Then I see the order creation message
	And I verify the product details on order template page
	|	orderTemplate.grid.sku	|	orderTemplate.grid.sku.price	|	orderTemplate.grid.sku.quantity	|
	And I logout from the storefront
	
Scenario: Create the order template from search lister list page
		And I search for the product with text "*"
		And I get the list of products
		And I change the product view as list view
		When I create the order template for product "orderTemplate.grid.sku"
		Then I see the order creation message
		And I verify the product details on order template page
		|	orderTemplate.grid.sku	|	orderTemplate.grid.sku.price	|	orderTemplate.grid.sku.quantity	|
		And I logout from the storefront
	
Scenario: Create the order template from PDP
	And I search for the product with text "*"
	And I change the product view as grid view
	And I get the list of products
	And I click on image of product "orderTemplate.grid.sku"
	When I create the order template for product on pdp
	Then I see the order creation message
	And I verify the product details on order template page
	|	orderTemplate.grid.sku	|	orderTemplate.grid.sku.price	|	orderTemplate.grid.sku.quantity	|
	And I logout from the storefront

Scenario: Create the order template from shopping cart page
	And I search for the product with text "*"
	And I change the product view as grid view
	And I get the list of products
	And I add product "orderTemplate.grid.sku" from grid view to shopping cart
	When I create the order template for product on shopping cart page
	Then I see the order creation message
	And I verify the product details on order template page
	|	orderTemplate.grid.sku	|	orderTemplate.grid.sku.price	|	orderTemplate.grid.sku.quantity	|
	And I logout from the storefront
	
