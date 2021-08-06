Feature: As a test user 
	I want to login to backoffice with valid login credentials

Scenario: Log in to backoffice with valid credential
	Given I navigate to backoffice page
	And I login to backoffice
	And I logout from the backoffice