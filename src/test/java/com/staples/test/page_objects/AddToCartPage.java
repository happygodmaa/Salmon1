package com.staples.test.page_objects;

import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.staples.test.framework.PageObject;

public class AddToCartPage extends PageObject{
	private static Logger LOG = LoggerFactory.getLogger(AddToCartPage.class);
	private By shoppingCartPli_sku				= By.cssSelector("div.pli-description>p.product-id");
	private By shoppingCartPli_qunatity			= By.cssSelector("input.form-control.quantity.js-quantity.js-product-qty");
	private By shoppingCartPli_price			= By.cssSelector("div.col-sm-2.hidden-xs.list-item.column-price.single-price");
	private By shoppingCartPli_totalPrice		= By.cssSelector("div.col-xs-3.col-sm-2.list-item.column-price>div");
	private By shoppingCartPli_totalCost		= By.cssSelector("dd.total-price");
	
	public String getShoppingCartPliSku() {
		String pliSku = null; 
		if(isElementPresent(shoppingCartPli_sku)) {
			pliSku = webDriver.findElement(shoppingCartPli_sku).getText();
		}
		else {
			customAssertFailure(shoppingCartPli_sku);
		}
		return pliSku;
	}
	
	public String getShoppingCartPliQuantity() {
		String pliQuantity = null; 
		if(isElementPresent(shoppingCartPli_qunatity)) {
			pliQuantity = webDriver.findElement(shoppingCartPli_qunatity).getAttribute("value");
		}
		else {
			customAssertFailure(shoppingCartPli_qunatity);
		}
		return pliQuantity;
	}
	
	public String getShoppingCartPliPrice() {
		String pliPrice = null; 
		if(isElementPresent(shoppingCartPli_price)) {
			pliPrice = webDriver.findElement(shoppingCartPli_price).getText();
		}
		else {
			customAssertFailure(shoppingCartPli_price);
		}
		return pliPrice;
	}
	
	public boolean getShoppingCartPliTotalCost() {
		boolean plitotalCostStatus = false;
		String pliTotalCost = null; 
		if(isElementPresent(shoppingCartPli_totalCost)) {
			pliTotalCost = webDriver.findElement(shoppingCartPli_totalCost).getText();
			if(pliTotalCost == null || pliTotalCost.contains("N/A") || pliTotalCost.isEmpty()) {
				plitotalCostStatus = false;
			}
			else {
				plitotalCostStatus = true;
			}
		}
		else {
			customAssertFailure(shoppingCartPli_totalCost);
		}
		return plitotalCostStatus;
	}
	
	public boolean getShoppingCartPliTotalPrice() {
		boolean plitotalPriceStatus = false;
		String pliTotalCost = null; 
		if(isElementPresent(shoppingCartPli_totalPrice)) {
			pliTotalCost = webDriver.findElement(shoppingCartPli_totalPrice).getText();
			if(!(pliTotalCost == null || pliTotalCost.contains("N/A") || pliTotalCost.isEmpty())) {
				plitotalPriceStatus = true;
			}
		}
		else {
			customAssertFailure(shoppingCartPli_totalPrice);
		}
		return plitotalPriceStatus;
	}
	
}
