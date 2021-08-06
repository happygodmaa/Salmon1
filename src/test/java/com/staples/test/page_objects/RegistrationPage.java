package com.staples.test.page_objects;


import org.openqa.selenium.By;
import org.testng.Assert;
import com.staples.test.framework.PageObject;
import com.staples.test.framework.helpers.PropertyReader;
import com.staples.test.framework.helpers.utils.RandomGenerator;

public class RegistrationPage extends PageObject {

  // private static final Logger LOG = LoggerFactory.getLogger(RegistrationPage.class);

  private By individual_radio_button = By.cssSelector("label[for=private]");
  private By business_radio_button = By.cssSelector("label[for=smb]");
  private By email = By.cssSelector("input[data-bv-field=RegisterUserFullEmail_Login]");
  private By email_confirmation =
      By.cssSelector("input[data-bv-field=RegisterUserFullEmail_EmailConfirmation]");
  private By password = By.cssSelector("input[data-bv-field=RegisterUserFullEmail_Password]");
  private By password_confirmation =
      By.cssSelector("input[data-bv-field=RegisterUserFullEmail_PasswordConfirmation]");

  private By company_name = By.cssSelector("input#CompanyInformationForm_CompanyName");
  // private By vat_number = By.cssSelector("input#CompanyInformationForm_TaxationID");
  private By company_registartion_number =
      By.cssSelector("input#CompanyInformationForm_CompanyRegistrationNumber");

  private By first_name = By.cssSelector("input[data-bv-field=AddressForm_FirstName]");
  private By last_name = By.cssSelector("input[data-bv-field=AddressForm_LastName]");

  private By company_first_name = By.cssSelector("input#CompanyInformationForm_FirstName");
  private By company_last_name = By.cssSelector("input#CompanyInformationForm_LastName");

  // private By country_dropdown = By.cssSelector("select#AddressForm_CountryCode");
  private By address1 = By.cssSelector("input[data-bv-field=AddressForm_Address1]");
  private By city = By.cssSelector("input[data-bv-field=AddressForm_City]");
  private By zip = By.cssSelector("input[data-bv-field=AddressForm_PostalCode]");
  // private By state_county_dropdown = By.cssSelector("select[data-bv-field=AddressForm_State]");
  private By create_account_button = By.cssSelector("button[value=CreateAccount]");
  private By error_msg = By.cssSelector("div.alert.alert-danger");
  private By login_name = By.cssSelector("span.hidden-xs.login-name");
  private By b2c_phone = By.cssSelector("input#addressInformation_PhoneBusiness");
  private By b2b_phone = By.cssSelector("input#CompanyInformationForm_PhoneBusiness");

  String email1;

  public void emailEntered() {
    email1 = RandomGenerator.randomEmailAddress(5);
    System.out.println(email1);

    if (isElementPresent(email)) {
      waitForExpectedElement(email).sendKeys(email1);
      // waitForExpectedElement(email).sendKeys(PropertyReader.getProp("private_registration.email"));
    } else {
      customAssertFailure(email);
    }
    if (isElementPresent(email_confirmation)) {
      waitForExpectedElement(email_confirmation).sendKeys(email1);
    } else {
      customAssertFailure(email_confirmation);
    }
  }

  public void same_emailEntered() {
    System.out.println("same email---" + email1);
    if (isElementPresent(email)) {
      waitForExpectedElement(email).sendKeys(email1);

    } else {
      customAssertFailure(email);
    }
    if (isElementPresent(email_confirmation)) {
      waitForExpectedElement(email_confirmation).sendKeys(email1);

      // waitForExpectedElement(email_confirmation).sendKeys(PropertyReader.getProp("private_registration.email"));
    } else {
      customAssertFailure(email_confirmation);
    }
  }

  public void privateUserDetailsEntered() {
    if (isElementPresent(individual_radio_button)) {
      waitForExpectedElement(individual_radio_button).click();
    } else {
      customAssertFailure(individual_radio_button);
    }
    if (isElementPresent(first_name)) {
      waitForExpectedElement(first_name)
          .sendKeys(PropertyReader.getProp("registration.first_name"));
    } else {
      customAssertFailure(first_name);
    }
    if (isElementPresent(last_name)) {
      waitForExpectedElement(last_name).sendKeys(PropertyReader.getProp("registration.last_name"));
    } else {
      customAssertFailure(last_name);
    }
    if (isElementPresent(b2c_phone)) {
      waitForExpectedElement(b2c_phone).sendKeys(PropertyReader.getProp("registration.phone"));
    } else {
      customAssertFailure(b2c_phone);
    }

  }

  public void businessUserDetailsEntered() {
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    if (isElementPresent(business_radio_button)) {
      waitForExpectedElement(business_radio_button).click();
    } else {
      customAssertFailure(business_radio_button);
    }
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    if (isElementPresent(company_name)) {
      waitForExpectedElement(company_name)
          .sendKeys(PropertyReader.getProp("registration_company.name"));
    } else {
      customAssertFailure(company_name);
    }
    if (isElementPresent(company_registartion_number)) {
      waitForExpectedElement(company_registartion_number)
          .sendKeys(PropertyReader.getProp("registration_company.registartion_number"));
    } else {
      customAssertFailure(company_registartion_number);
    }
    if (isElementPresent(company_first_name)) {
      waitForExpectedElement(company_first_name)
          .sendKeys(PropertyReader.getProp("registration.first_name"));
    } else {
      customAssertFailure(company_first_name);
    }
    if (isElementPresent(company_last_name)) {
      waitForExpectedElement(company_last_name)
          .sendKeys(PropertyReader.getProp("registration.last_name"));
    } else {
      customAssertFailure(company_last_name);
    }

    if (isElementPresent(b2b_phone)) {
      waitForExpectedElement(b2b_phone).sendKeys(PropertyReader.getProp("registration.phone"));
    } else {
      customAssertFailure(b2b_phone);
    }
  }

  public void otherMandatoryDetailsEntered() {

    if (isElementPresent(password)) {
      waitForExpectedElement(password).sendKeys(PropertyReader.getProp("registration.password"));
    } else {
      customAssertFailure(password);
    }
    if (isElementPresent(password_confirmation)) {
      waitForExpectedElement(password_confirmation)
          .sendKeys(PropertyReader.getProp("registration.password"));
    } else {
      customAssertFailure(password_confirmation);
    }

    if (isElementPresent(address1)) {
      waitForExpectedElement(address1).sendKeys(PropertyReader.getProp("registration.address1"));
    } else {
      customAssertFailure(address1);
    }
    if (isElementPresent(city)) {
      waitForExpectedElement(city).sendKeys(PropertyReader.getProp("registration.city"));
    } else {
      customAssertFailure(city);
    }
    if (isElementPresent(zip)) {
      waitForExpectedElement(zip).sendKeys(PropertyReader.getProp("registration.zip"));
    } else {
      customAssertFailure(zip);
    }
    // if (isElementPresent(state_county_dropdown)) {
    // waitForExpectedElement(state_county_dropdown).click();
    // Select dropdown1 = new Select(webDriver.findElement(state_county_dropdown));
    // dropdown1.selectByVisibleText(PropertyReader.getProp("registration.city"));
    // } else {
    // customAssertFailure(state_county_dropdown);
    // }
    if (isElementPresent(create_account_button)) {
      waitForExpectedElement(create_account_button).click();
    } else {
      customAssertFailure(create_account_button);
    }
    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  public void userRegistration() {
    if (isElementPresent(login_name)) {
      waitForExpectedElement(login_name);
    } else {
      customAssertFailure(login_name);
    }
    String entered_name = PropertyReader.getProp("registration.first_name") + " "
        + PropertyReader.getProp("registration.last_name");
    Assert.assertEquals(webDriver.findElement(login_name).getText(), entered_name.toUpperCase());
    System.out
        .println(webDriver.findElement(login_name).getText() + "----" + entered_name.toUpperCase());
  }

  public void verifyErrorMessage() {
    if (isElementPresent(error_msg)) {
      waitForExpectedElement(error_msg);
    } else {
      customAssertFailure(error_msg);
    }
    Assert.assertEquals(webDriver.findElement(error_msg).getText(),
        PropertyReader.getProp("expected_error_msg"));
    System.out.println(PropertyReader.getProp("expected_error_msg") + "--------expected");

    System.out.println(webDriver.findElement(error_msg).getText() + "-----------actual");
  }

}

