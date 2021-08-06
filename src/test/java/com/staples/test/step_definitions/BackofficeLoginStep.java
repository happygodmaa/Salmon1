package com.staples.test.step_definitions;

import com.staples.test.page_objects.BackofficeLoginPage;
import cucumber.api.java.en.And;

public class BackofficeLoginStep {

  BackofficeLoginPage backofficeLoginPage;

  public BackofficeLoginStep(BackofficeLoginPage backofficeLoginPage) {
    this.backofficeLoginPage = backofficeLoginPage;
  }

  @And("^I login to backoffice$")
  public void I_login_to_backoffice() {
    backofficeLoginPage.loginToBackoffice();
  }

  @And("^I logout from the backoffice$")
  public void l_logout_from_the_backoffice() {
    backofficeLoginPage.loginToBackoffice();
  }
}
