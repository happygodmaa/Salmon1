package com.staples.test.step_definitions;

import com.staples.test.framework.helpers.UrlBuilder;
import com.staples.test.framework.helpers.WebDriverHelper;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;

public class NavigationStep {

  @Given("^I navigates to backoffice$")
  public void user_navigates_to_backoffice() {
    UrlBuilder.OpenBackofficeUrl();
  }

  @Given("^I navigate to storefront page$")
  public void user_navigate_to_storefront_page() {
    UrlBuilder.startAtHomePage();
  }

  @And("^I close browser$")
  public void close_browser() {
    WebDriverHelper.REAL_DRIVER.close();
  }

}
