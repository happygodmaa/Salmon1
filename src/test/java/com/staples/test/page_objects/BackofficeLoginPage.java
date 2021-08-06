package com.staples.test.page_objects;

import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.staples.test.framework.PageObject;
import com.staples.test.framework.helpers.PropertyReader;

public class BackofficeLoginPage extends PageObject {
  private static final Logger LOG = LoggerFactory.getLogger(BackofficeLoginPage.class);

  private By backoffice_userName = By.id("LoginForm_Login");
  private By backoffice_password = By.id("LoginForm_Password");
  private By backoffice_organization = By.id("LoginForm_RegistrationDomain");
  private By backoffice_signIn = By.name("submit");

  private By storefront_myAccountLink = By.cssSelector("a.my-account-link>span:nth-child(3)");
  private By stroefront_staplesLogo = By.cssSelector("div.col-sm-3.logo-wrapper.hidden-xs>a>img");
  private By backoffice_logOut = By.cssSelector("a[title='Logout']");



  public void loginToBackoffice() {
    if (isElementPresent(backoffice_userName)) {
      waitForExpectedElement(backoffice_userName).click();
      waitForExpectedElement(backoffice_userName)
          .sendKeys(PropertyReader.getProp("backoffice.user.name"));
      if (isElementPresent(backoffice_password)) {
        waitForExpectedElement(backoffice_password).click();
        waitForExpectedElement(backoffice_password)
            .sendKeys(PropertyReader.getProp("storefront.password"));
        clickOnStorefrontLoginButton();
        // clickOnMyAccountLink();
      } else {

        customAssertFailure(backoffice_password);
      }
    } else {
      customAssertFailure(backoffice_userName);
    }
  }

  private void clickOnStorefrontLoginButton() {
    if (isElementPresent(backoffice_signIn)) {
      waitForExpectedElement(backoffice_signIn).click();
    } else {
      customAssertFailure(backoffice_signIn);
    }
  }

  private void clickOnMyAccountLink() {
    if (isElementPresent(storefront_myAccountLink)) {
      waitForExpectedElement(storefront_myAccountLink).click();
    } else {
      customAssertFailure(storefront_myAccountLink);
    }
  }

  public void logOutFromStorefront() {
    if (isElementPresent(stroefront_staplesLogo)) {
      waitForExpectedElement(stroefront_staplesLogo).click();
      if (isElementPresent(backoffice_logOut)) {
        waitForExpectedElement(backoffice_logOut).click();
        LOG.info("Logged out from the storefront");
      } else {
        customAssertFailure(backoffice_logOut);
      }
    } else {
      customAssertFailure(stroefront_staplesLogo);
    }
  }

}
