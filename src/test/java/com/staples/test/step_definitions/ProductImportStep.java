package com.staples.test.step_definitions;

import com.staples.test.framework.helpers.PropertyReader;
import com.staples.test.page_objects.ProductImportPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;

public class ProductImportStep {
	
	ProductImportPage productImportPage;
	
	public ProductImportStep(ProductImportPage productImportPage)
	{
		this.productImportPage = productImportPage;
	}
	
	@And("I login to backoffice$")
	public void I_login_to_backoffice() {
		productImportPage.loginToBackoffice();
	}

	@And("I select the channel name as \"(.*?)\"$")
	public void user_select_the_channel_name_as(String channelName) {
		productImportPage.selectChannelFromBackOffice(PropertyReader.getProp(channelName));
		
	}

	@And("I click on catalogs menu$")
	public void user_click_on_catalogs_menu() {
	    productImportPage.navigateToChannelCatalog();
	    
	}

	@And("I click on import-export sub-menu$")
	public void user_click_on_import_export_sub_menu() {
	    productImportPage.clickOnProductImportExportButton();
	    
	}

	@And("I click on upload button of import-export files$")
	public void user_click_on_upload_button_of_import_export_files() {
		productImportPage.clickOnProductFileUploadButton();
		
	}

	@And("I deleted the product file \"(.*?)\" if it already uploaded$")
	public void user_deleted_the_product_file_if_it_already_uploaded(String fileName) {
		productImportPage.verifyFileExistInBackoffice(PropertyReader.getProp(fileName));
	    
	}
	@And("I upload the product file \"(.*?)\"$")
	public void user_upload_the_product_file(String fileName) {
	    productImportPage.uploadProductImportFile(PropertyReader.getProp(fileName));
	    
	}
	
	@And("I click on product import button$")
	public void user_click_on_product_import_button() {
		productImportPage.clickOnProductImportButton();
	}

	@And("I click on radio button with file \"(.*?)\"$")
	public void user_click_on_radio_button_with_file(String fileName) {
		productImportPage.selectProductFileForImport(PropertyReader.getProp(fileName));
	}
	
	@And("I click on product import next button$")
	public void user_click_on_product_import_next_button() {
		productImportPage.clickOnProductImportNextButton();
	}

	@And("I click on product import validate file next button$")
	public void user_click_on_product_import_validate_file_next_button() {
		productImportPage.ClickOnProductImportValidateNextButton();
	}

	@And("I click product import importMode button$")
	public void user_click_product_import_importMode_button() {
		productImportPage.clickOnProductImport_ImportButton();
	}
	
	@And("I click on product import status of last pagecursor button$")
	public void user_click_on_product_import_status_of_last_pagecursor_button() {
		productImportPage.clickOnProductImportStatusLastPage();
	}

	@And("I click on refresh button of product import status page$")
	public void user_click_on_refresh_button_of_product_import_status_page() {
		productImportPage.clickOnProductImportRefreshButton();
	}

	@Then("I verify product import status \"(.*?)\"$")
	public void user_verify_pproduct_import_status(String importStatus) throws InterruptedException {
		productImportPage.verifyProductImportStatus(PropertyReader.getProp(importStatus));
	}
	
}
