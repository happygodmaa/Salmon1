@registration
Feature: As a user 
	I want to register as private user

Background: 
	Given I navigate to storefront page	
		
Scenario: Private User registration success and re-registartion error
	And I accept all cookies
	And I navigate to Registration page
	And I enter private user specific details
	And I enter private user email
	And I enter other mandatory details
	Then I verify user is registered
	And I logout from the storefront
	And I navigate to Registration page
	And I enter private user specific details
	And I enter samePrivate user email
	And I enter other mandatory details
	Then I verify error message
	#And I close browser

Scenario: Business User registration success and re-registartion error
	#And I accept all cookies
	And I navigate to Registration page
	And I enter business user specific details
	And I enter business user email
	And I enter other mandatory details
	Then I verify user is registered
	And I logout from the storefront
	And I navigate to Registration page
	And I enter business user specific details
	And I enter sameBusiness user email
	And I enter other mandatory details
	Then I verify error message
	