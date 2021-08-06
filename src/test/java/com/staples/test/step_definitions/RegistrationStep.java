package com.staples.test.step_definitions;

import com.staples.test.page_objects.RegistrationPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;

public class RegistrationStep {


  RegistrationPage registrationPage;
  // https://www.youtube.com/watch?v=-OFO7CexSII
  // design pattern 25 mins

  public RegistrationStep(RegistrationPage registrationPage) {
    this.registrationPage = registrationPage;
    // System.out.println("registrationstep constructor");
  }

  @And("^I enter private user specific details$")
  public void I_enter_private_user_specific_details() {
    // System.out.println(registrationPage);
    registrationPage.privateUserDetailsEntered();
  }

  @And("^I enter private user email$")
  public void I_enter_private_user_email() {
    registrationPage.emailEntered();
  }

  @And("^I enter samePrivate user email$")
  public void I_enter_samePrivate_user_email() {
    registrationPage.same_emailEntered();
  }

  @And("^I enter business user specific details$")
  public void i_enter_business_user_specific_details() {
    // System.out.println(registrationPage);
    registrationPage.businessUserDetailsEntered();
  }

  @And("^I enter business user email$")
  public void I_enter_business_user_email() {
    registrationPage.emailEntered();
  }

  @And("^I enter sameBusiness user email$")
  public void I_enter_sameBusiness_user_email() {
    registrationPage.same_emailEntered();
  }

  @And("^I enter other mandatory details$")
  public void i_enter_other_mandatory_details() {
    // System.out.println(registrationPage);
    registrationPage.otherMandatoryDetailsEntered();
  }


  @Then("^I verify user is registered$")
  public void I_verify_user_is_registered() {
    registrationPage.userRegistration();
  }

  @Then("^I verify error message$")
  public void Then_I_verify_error_message() {
    registrationPage.verifyErrorMessage();

  }

  // @After
  // public void tearDown() {
  // WebDriverHelper.REAL_DRIVER.close();
  // }

}
