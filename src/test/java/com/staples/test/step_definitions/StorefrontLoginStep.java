package com.staples.test.step_definitions;

import com.staples.test.page_objects.StorefrontLoginPage;
import cucumber.api.java.en.And;

public class StorefrontLoginStep {

  StorefrontLoginPage storefrontLoginPage;

  public StorefrontLoginStep(StorefrontLoginPage storefrontLoginPage) {

    // System.out.println(storefrontLoginPage + "inside constructor--before value");

    this.storefrontLoginPage = storefrontLoginPage;

    // System.out.println(storefrontLoginPage + "inside constructor--after value");

  }

  @And("^I navigate to Registration page$")
  public void I_navigate_to_Registration_page() {
    // System.out.println(storefrontLoginPage + "I navigate to Registration page");
    storefrontLoginPage.navigateToRegistrationpage();
  }

  @And("^I accept all cookies$")
  public void user_accept_all_cookies() {
    storefrontLoginPage.acceptCookies();
  }

  @And("^I login to storefront$")
  public void I_login_to_storefront() {
    storefrontLoginPage.loginToStorefront();
  }

  @And("^I Search and add product$")
  public void I_Search_and_add_product() {
    storefrontLoginPage.searchAndAddProduct();
  }


  @And("^I logout from the storefront$")
  public void l_logout_from_the_storefront() {
    storefrontLoginPage.logOutFromStorefront();
  }
}
