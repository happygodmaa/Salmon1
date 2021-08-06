package com.staples.test.page_objects;

import java.util.Set;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.staples.test.framework.PageObject;
import com.staples.test.framework.helpers.PropertyReader;
import com.staples.test.framework.helpers.WebDriverHelper;

public class StorefrontLoginPage extends PageObject {
  private static final Logger LOG = LoggerFactory.getLogger(StorefrontLoginPage.class);
  private By storefront_userName = By.id("ShopLoginForm_Login");
  private By storefront_password = By.id("ShopLoginForm_Password");
  // private By storefront_signIn = By.cssSelector("div>button.btn.btn-primary");
  private By storefront_signIn = By.cssSelector("span.hidden-xs");
  private By storefront_Login = By.cssSelector("button[name='login']");
  private By storefront_myAccountLink = By.cssSelector("a.my-account-link>span:nth-child(3)");
  // private By stroefront_staplesLogo =
  // By.cssSelector("div.col-sm-3.logo-wrapper.hidden-xs>a>img");
  private By storefront_logOut = By.cssSelector("a.my-account-link.my-account-logout");
  private By registration_link = By.cssSelector("a[class^=ish]> span.hidden-xs");
  private By search_Field = By.id("spls-header-search-bar-id");
  // private By search_Field=By.cssSelector("input[name=\"SearchTerm\"]");
  private By acceptCookiesButton = By.cssSelector("button.s-accept-all-cookies");
  private By welcomePopupCloseIcon = By.cssSelector("span.close");
  private By addToCart_list = By.cssSelector(
      "button.btn.spls-add-to-cart-btn.btn-lg.btn-block.btn-primary.stock-check:not([disabled=''])");

  // private By firstAddToCart = addToCart_list[1];

  public void acceptCookies() {
    try {
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    String mainWindow = WebDriverHelper.REAL_DRIVER.getWindowHandle();
    Set<String> allWindows = WebDriverHelper.REAL_DRIVER.getWindowHandles();
    for (String s : allWindows) {
      if (s != mainWindow) {
        WebDriverHelper.REAL_DRIVER.switchTo().window(s);
        if (isElementPresent(acceptCookiesButton)) {
          waitForExpectedElement(acceptCookiesButton).click();
        } else if (isElementPresent(welcomePopupCloseIcon)) {
          waitForExpectedElement(welcomePopupCloseIcon).click();
        } else {
          customAssertFailure(acceptCookiesButton);
        }
      }
    }
    WebDriverHelper.REAL_DRIVER.switchTo().window(mainWindow);
  }

  public void navigateToRegistrationpage() {
    if (isElementPresent(registration_link)) {
      waitForExpectedElement(registration_link).click();
    } else {
      customAssertFailure(registration_link);
    }
  }

  public void loginToStorefront() {

    if (isElementPresent(storefront_signIn)) {
      System.out.println(storefront_signIn);
      waitForExpectedElement(storefront_signIn).click();
    } else {
      customAssertFailure(storefront_signIn);
    }
    if (isElementPresent(storefront_userName)) {
      waitForExpectedElement(storefront_userName).click();
      waitForExpectedElement(storefront_userName)
          .sendKeys(PropertyReader.getProp("storefront.user.name"));

    } else {
      customAssertFailure(storefront_userName);
    }

    if (isElementPresent(storefront_password)) {
      waitForExpectedElement(storefront_password).click();
      waitForExpectedElement(storefront_password)
          .sendKeys(PropertyReader.getProp("storefront.password"));
      // clickOnMyAccountLink();
    } else {
      customAssertFailure(storefront_password);
    }

    if (isElementPresent(storefront_Login)) {
      System.out.println(storefront_Login);
      waitForExpectedElement(storefront_Login).click();
    } else {
      customAssertFailure(storefront_Login);
    }
  }

  public void searchAndAddProduct() {
    if (isElementPresent(search_Field)) {
      waitForExpectedElement(search_Field).click();
      waitForExpectedElement(search_Field).sendKeys(PropertyReader.getProp("product.id"));
      waitForExpectedElement(search_Field).submit();
      System.out.println(addToCart_list);
    } else {
      customAssertFailure(search_Field);
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

    if (isElementPresent(storefront_logOut)) {
      waitForExpectedElement(storefront_logOut).click();
      LOG.info("Logged out from the storefront");
    } else {
      customAssertFailure(storefront_logOut);
    }
    System.out.println("logout hello");
    // webDriver.close();
  }
}

// if (isElementPresent(stroefront_staplesLogo)) {
// waitForExpectedElement(stroefront_staplesLogo).click();
