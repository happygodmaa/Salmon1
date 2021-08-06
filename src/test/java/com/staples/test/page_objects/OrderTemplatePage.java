package com.staples.test.page_objects;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.staples.test.framework.PageObject;
import com.staples.test.framework.helpers.utils.RandomGenerator;

public class OrderTemplatePage extends PageObject {
	private static final Logger LOG = LoggerFactory.getLogger(OrderTemplatePage.class); 
	private By search_EditBox						=	By.cssSelector("input.form-control.searchTerm");
	private By searchIcon							=	By.cssSelector("span.glyphicon.glyphicon-search");
	private By searchProductLists					= 	By.cssSelector("div.product-list-item.col-md-12.list-view");
	private By listerGridIcon						=	By.cssSelector("span.glyphicon.glyphicon-th");
	private By listerListIcon						=	By.cssSelector("a>span.glyphicon.glyphicon-list");
	private By listerOrederTemplateIcons_Lists		=	By.cssSelector("button>span.glyphicon.glyphicon-list");
	private By lister_SkuLists						= 	By.cssSelector("a.product-title[href*='SKU']");
	private By lister_paginationIcon				= 	By.cssSelector("li.pagination-list-next>a");
	private By existing_orderTemplateLists			= 	By.cssSelector("div.form-group>div.radio");				
	private By orderTemplate_radioButton			= 	By.id("NewWishlistRadioButton");
	private By orderTemplateName_editBox			= 	By.id("NewWishlistName");
	private By orderTemplate_createButton			= 	By.cssSelector("div.modal-footer>button:first-child");
	private By orderTemplatelinkText				= 	By.cssSelector("div.modal-body>p>a");
	private By orderTemplatePage_productID			= 	By.cssSelector("p.product-id");
	private By orderTemplatePage_productQuantity	= 	By.cssSelector("div.col-lg-3.list-item.form-inline>input");
	private By orderTemplatePage_productPrice		= 	By.cssSelector("div.col-sm-2.col-xs-3.list-item.column-price");
	private By orderTemplatePage_addToCartButton	= 	By.cssSelector("button.btn.btn-primary.pull-right");
	private By productImageLists					= 	By.cssSelector("div.product-image-container>a>img");
	private By orderTemplateIcon_PDP				= 	By.cssSelector("li>button>span.glyphicon.glyphicon-list");
	private By addToCartIcon_grid					= 	By.cssSelector("button.btn.btn-lg.btn-block.btn-primary");
	private By OrderTemplateButton_shoppingCart		= 	By.id("cart-to-wishlist");
	private By grid_SkuLists						=	By.xpath("//div/div/button[@class='btn btn-lg btn-block btn-primary']/../../../input[@name='SKU']");
	private By orderTemplateEditBox_shoppingCart	= 	By.cssSelector("div.form-group.has-feedback>div.col-sm-8>input");
	private By orderTemplateCreate_shoppingCart		=	By.cssSelector("div.modal-footer>button.btn.btn-primary");
	public static String orderTemplantName = null;

	
	private void enterNameOfTheProductOnSearchBox(String productName) {
			if(isElementPresent(search_EditBox)) {
				waitForExpectedElement(search_EditBox).sendKeys(productName);
			}
			else {
				customAssertFailure(search_EditBox);
			}
		}
	
	private void clickOnSearchButtonIcon() {
		if(isElementPresent(searchIcon)) {
			waitForExpectedElement(searchIcon).click();
		}
		else {
			customAssertFailure(searchIcon);
		}
	}
		
	private void verifyAndCreateNewOrderTemplate()
	{
		List<WebElement> existingTemplateLists = webDriver.findElements(existing_orderTemplateLists);
		if(existingTemplateLists.size() > 0) {
			if(isElementPresent(orderTemplate_radioButton)) {
					waitForExpectedElement(orderTemplate_radioButton).click();
					waitForPageLoad(webDriver);
					orderTemplateCreateAction();
				}
				else {
					customAssertFailure(orderTemplate_radioButton);
				}
		}
		else {
			orderTemplateCreateAction();
		}
	}

	/**
	 * This method is used to create order template from grid,list,pdp and cart page
	 */
	private void orderTemplateCreateAction() {
		orderTemplantName = RandomGenerator.randomAlphabetic(20);
		if(isElementPresent(orderTemplateName_editBox)) {
			waitForExpectedElement(orderTemplateName_editBox).clear();
			waitForExpectedElement(orderTemplateName_editBox).sendKeys(orderTemplantName);
			if(isElementPresent(orderTemplate_createButton)) {
				waitForExpectedElement(orderTemplate_createButton).click();
				LOG.info("Order template created with name: ["+orderTemplantName+"]");
			}
			else {
				customAssertFailure(orderTemplate_createButton);
			}
		}
		else {
			customAssertFailure(orderTemplateName_editBox);
		}
	}

	/**
	 * This method return the product id from order template page
	 * @return: productID
	 */
	public String getProductIdOnOrderTemplatePage()
	{
		String productID = null;
		if(isElementPresent(orderTemplatePage_productID)) {
			productID =  waitForExpectedElement(orderTemplatePage_productID).getText();
		}
		else {
			customAssertFailure(orderTemplatePage_productID);
		}
		return productID;
	}
	
	/**
	 * This method return th product price on order tempalte page
	 * @return: productPrice
	 */
	public String getProductPriceOnOrderTemplatePage() {
		String productPrice = null;
		if(isElementPresent(orderTemplatePage_productPrice)) {
			productPrice =  waitForExpectedElement(orderTemplatePage_productPrice).getText();
		}
		else {
			customAssertFailure(orderTemplatePage_productPrice);
		}
		return productPrice;
	}
	
	/**
	 * This method return the product quantity from order template page
	 * @return: productQuantity
	 */
	public String getProductQuantityOnOrderTemplatePage() {
		String productQuantity = null;
		if(isElementPresent(orderTemplatePage_productQuantity)) {
			productQuantity = waitForExpectedElement(orderTemplatePage_productQuantity).getAttribute("value");
		}
		else {
			customAssertFailure(orderTemplatePage_productQuantity);
		}
		return productQuantity;
	}
	
	/**
	 * This method return whether addToCart button is active or not
	 * @return: true/false
	 */
	public boolean getStatusOfAddToCartButtonOnOrderTemplatePage()
	{
		boolean orderTemplateButtonStatus = false;
		if(isElementPresent(orderTemplatePage_addToCartButton)) {
			orderTemplateButtonStatus = waitForExpectedElement(orderTemplatePage_addToCartButton).isEnabled();
		}
		else {
			customAssertFailure(orderTemplatePage_addToCartButton);
		}
		return orderTemplateButtonStatus;
	}
	
	/**
	 * Search for the product using search edit box
	 * @param searchText
	 */
	public void searchForProduct(String searchText) {
		enterNameOfTheProductOnSearchBox(searchText);
		clickOnSearchButtonIcon();
	}
	
	/**
	 * This method return the list of product count with respect to serach text
	 * @return: productCount
	 */
	public int getProductCountSearchResult() {
		int productCount = 0;
		if(isElementPresent(searchProductLists)) {
			List<WebElement> productLists = presenceOfAllElementsLocatedBy(searchProductLists);
			productCount = productLists.size();
		}
		else {
			customAssertFailure(searchProductLists);
		}
		return productCount;
	}
	
	/**
	 * THis method used to switch the product view to grid view
	 */
	public void switchToListerGridView() {
		if(isElementPresent(listerGridIcon)) {
			waitForExpectedElement(listerGridIcon).click();
		}
		else {
			customAssertFailure(listerGridIcon);
		}
	}
	
	/**
	 * THis method used to switch the product view to list view
	 */
	public void switchToListerListView() {
		if(isElementPresent(listerListIcon)) {
			waitForExpectedElement(listerListIcon).click();
		}
		else {
			customAssertFailure(listerListIcon);
		}
	}
	
	/**
	 * This method used for creation of order template
	 * @param sku
	 */
	public void createOrderTemplate(String sku)
	{
		clickOnElementBasedOnIndex(sku,listerOrederTemplateIcons_Lists,lister_SkuLists,lister_paginationIcon);
		verifyAndCreateNewOrderTemplate();
	}
	
	/**
	 * This method verifies orderTemplate name exist with the order template message or not
	 */
	public void clickOnOrderTemplateLink() {
		if(isElementPresent(orderTemplatelinkText)) {
				waitForExpectedElement(orderTemplatelinkText).click();
			}
			else {
				customAssertFailure(orderTemplatelinkText);
			}
	}
	
	public String getOrderTemplateMessage() {
		String orderTemplateMessage = null;
		if(isElementPresent(orderTemplatelinkText)) {
			orderTemplateMessage =  waitForExpectedElement(orderTemplatelinkText).getText().replaceAll("\"", "");
		}
		else {
			customAssertFailure(orderTemplatelinkText);
		}
		return orderTemplateMessage;
	}
	
	/**
	 * This method is used to click on specific product image	
	 * @param sku
	 */
	public void clickOnProductImage(String sku) {
		clickOnElementBasedOnIndex(sku,productImageLists,lister_SkuLists,lister_paginationIcon);
	}
	
	public void clickOnGridListerAddToCartButton(String sku) {
		clickOnElementBasedOnIndex(sku, addToCartIcon_grid, grid_SkuLists,lister_paginationIcon);
	}
	
	public void createOrderTemplateOnPDP()
	{
		if(isElementPresent(orderTemplateIcon_PDP)) {
			waitForExpectedElement(orderTemplateIcon_PDP).click();
			verifyAndCreateNewOrderTemplate();
		}
		else {
			customAssertFailure(orderTemplateIcon_PDP);
		}
	}
	
	public void createOrderTemplateOnShippingCart() {
		clickOnShoppingCartOrderTemplateButton();
		enterAndCreateOrderTemplateOnSHoppingCart();
	}
	
	
	private void clickOnShoppingCartOrderTemplateButton() {
		if(isElementPresent(OrderTemplateButton_shoppingCart)) {
			waitForExpectedElement(OrderTemplateButton_shoppingCart).click();
		}
		else {
			customAssertFailure(OrderTemplateButton_shoppingCart);
		}
	}
	
	private void enterAndCreateOrderTemplateOnSHoppingCart() {
		orderTemplantName = RandomGenerator.randomAlphabetic(20);
		if(isElementPresent(orderTemplateEditBox_shoppingCart)) {
			waitForExpectedElement(orderTemplateEditBox_shoppingCart).sendKeys(orderTemplantName);
			if(isElementPresent(orderTemplateCreate_shoppingCart)) {
				waitForExpectedElement(orderTemplateCreate_shoppingCart).click();
			}
			else {
				customAssertFailure(OrderTemplateButton_shoppingCart);
			}
		}
		else {
			customAssertFailure(orderTemplateEditBox_shoppingCart);
		}
	}
}
