@SNE-377,@backoffice 
Feature: As a test user 
	I want to import product file to backoffice
		So that everytime i will have same set of product data across all the environments

Background: 
	#@XR-122
	Given I navigates to backoffice 
	And I login to backoffice
	
@XR-121 
Scenario Outline: Upload product file to the backoffice 
	And I select the channel name as "<channel.name>" 
	And I click on catalogs menu 
	And I click on import-export sub-menu 
	And I click on upload button of import-export files 
	And I deleted the product file "<product-file-name>" if it already uploaded 
	And I upload the product file "<product-file-name>" 
	And I click on catalogs menu 
	And I click on import-export sub-menu 
	And I click on product import button 
	And I click on radio button with file "<product-file-name>" 
	And I click on product import next button 
	And I click on product import validate file next button 
	And I click product import importMode button 
	And I click on product import status of last pagecursor button 
	And I click on refresh button of product import status page 
	Then I verify product import status "<import-status>" 
	
	Examples: 
	|		channel.name							|	product-file-name								|	import-status															|
	|		backoffice.channel.name		|	productImport.product.file.name	|	backoffice.product.import.complete.status	|	
	