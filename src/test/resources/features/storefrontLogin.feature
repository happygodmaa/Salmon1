@login 
Feature: As a test user 
	I want to login to storefront with valid login credentials

Scenario: Log in to storefront with valid credential
	Given I navigate to storefront page
	And I login to storefront
	And I logout from the storefront