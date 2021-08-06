package com.staples.test.step_definitions;

import java.util.List;
import com.staples.test.framework.helpers.PropertyReader;
import com.staples.test.page_objects.OrderTemplatePage;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class OrderTemplateStep {
	
	OrderTemplatePage orderTemplatePage;
	
	public OrderTemplateStep(OrderTemplatePage orderTemplatePage) {
		this.orderTemplatePage = orderTemplatePage;
	}
	
	@And("^I search for the product with text \"(.*?)\"$")
	public void i_search_for_the_product_with_text(String searchText)  {
	    orderTemplatePage.searchForProduct(searchText);
	}

	@And("^I get the list of products$")
	public void i_get_the_list_of_products()  {
	    int actualProductCount = orderTemplatePage.getProductCountSearchResult();
	    if(actualProductCount == 0) {
	    	assertEquals(actualProductCount,1,"Failed to get product list for the searched text");
	    }
	}

	@And("^I change the product view as grid view$")
	public void i_change_the_product_view_as_grid_view() {
	    orderTemplatePage.switchToListerGridView();
	}
	
	@And("^I change the product view as list view$")
	public void i_change_the_product_view_as_list_view() {
	    orderTemplatePage.switchToListerListView();
	}

	@When("^I create the order template for product \"(.*?)\"$")
	public void i_create_the_order_template_for_product(String sku) {
	    orderTemplatePage.createOrderTemplate(PropertyReader.getProp(sku));
	}

	@Then("^I see the order creation message$")
	public void i_see_the_order_creation_message() {
		String actualOrderTemplateName = orderTemplatePage.getOrderTemplateMessage();
		assertEquals(actualOrderTemplateName, OrderTemplatePage.orderTemplantName);
		orderTemplatePage.clickOnOrderTemplateLink();
	}

	@And("^I verify the product details on order template page")
	public void i_verify_the_product_details_on_order_template_page(List<String> orderLists) throws InterruptedException {
		String expectedProductID = PropertyReader.getProp(orderLists.get(0));
		String expectedProductPrice = PropertyReader.getProp(orderLists.get(1));
		String expectedProductQuantity = PropertyReader.getProp(orderLists.get(2));
		
		String actualProductID = orderTemplatePage.getProductIdOnOrderTemplatePage();
		String actualProductPrice = orderTemplatePage.getProductPriceOnOrderTemplatePage();
		String actualProductQuantity = orderTemplatePage.getProductQuantityOnOrderTemplatePage();
				
				
		assertTrue(actualProductID.contains(expectedProductID));
		assertTrue(actualProductPrice.contains(expectedProductPrice));
		assertEquals(actualProductQuantity,expectedProductQuantity);
		assertTrue(orderTemplatePage.getStatusOfAddToCartButtonOnOrderTemplatePage());
	}
	
	@And("^I click on image of product \"(.*?)\"$")
	public void i_click_image_of_product(String sku) throws InterruptedException {
	    orderTemplatePage.clickOnProductImage(PropertyReader.getProp(sku));
	}
	
	@When("^I create the order template for product on pdp$")
	public void i_create_the_order_template_for_product_on_pdp() throws InterruptedException {
		orderTemplatePage.createOrderTemplateOnPDP();
	}
	
	@And("^I add product \"(.*?)\" from grid view to shopping cart$")
	public void i_add_product_from_grid_view_to_shopping_cart(String sku) {
	    orderTemplatePage.clickOnGridListerAddToCartButton(PropertyReader.getProp(sku));
	}

	@When("^I create the order template for product on shopping cart page$")
	public void i_create_the_order_template_for_product_on_shopping_cart_page() {
	    orderTemplatePage.createOrderTemplateOnShippingCart();
	}

}
