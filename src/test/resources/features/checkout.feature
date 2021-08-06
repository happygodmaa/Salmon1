@checkout
Feature: As a user 
	I want to check end to end checkout transaction

Background: 
	Given I navigate to storefront page
	And I accept all cookies
	
Scenario: Private User checkout transaction
	And I login to storefront
	And I logout from the storefront
	#And I Search and add product

#	And I go to cart page
#	And I go to checkout page
#	And I go to shippingReview page
#	And I go to checkoutReview page
#	And I enter card details
#	Then I verify order is created