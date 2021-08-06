package com.staples.test.step_definitions;

import java.util.List;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import com.staples.test.framework.helpers.PropertyReader;
import com.staples.test.page_objects.AddToCartPage;

import cucumber.api.java.en.Then;

public class AddToCartSteps {
	AddToCartPage addCartPage;
	public AddToCartSteps(AddToCartPage addCartPage) {
		this.addCartPage = addCartPage;
	}
	
	
	@Then("^I verify product details on shopping cart page$")
	public void i_verify_product_details_on_shopping_cart_page(List<String> productDetails) {
		String shoppingCartPliSku = PropertyReader.getProp(productDetails.get(0));
		String shoppingCartPliPrice = PropertyReader.getProp(productDetails.get(1));
		String shoppingCartPliQuantity = PropertyReader.getProp(productDetails.get(2));
		//Performing assertion for PLI data sets
		assertTrue(addCartPage.getShoppingCartPliSku().contains(shoppingCartPliSku));
//		assertTrue(addCartPage.getShoppingCartPliPrice().contains(shoppingCartPliPrice));
		assertEquals(addCartPage.getShoppingCartPliQuantity(), shoppingCartPliQuantity);
//		assertTrue(addCartPage.getShoppingCartPliTotalPrice());
//		assertTrue(addCartPage.getShoppingCartPliTotalCost());
	}
}
