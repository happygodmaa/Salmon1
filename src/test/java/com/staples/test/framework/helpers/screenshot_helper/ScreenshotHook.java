package com.staples.test.framework.helpers.screenshot_helper;

import static com.staples.test.framework.helpers.WebDriverHelper.getWebDriver;
import java.util.Map;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.staples.test.framework.helpers.WebDriverHelper;
import cucumber.api.Scenario;
import cucumber.api.java.After;

public class ScreenshotHook {
  private static final Logger LOG = LoggerFactory.getLogger(ScreenshotHook.class);

  // Not Execute for any api tests
  @After
  public void embedScreenshot(Scenario scenario) {
    try {
      Map<String, Object> screenShots = ScreenshotHelper.getScreenShotsForCurrentTest();
      for (Map.Entry<String, Object> screenShot : screenShots.entrySet()) {
        scenario.write(screenShot.getKey());
        scenario.embed((byte[]) screenShot.getValue(), "image/png");
      }
      ScreenshotHelper.tidyUpAfterTestRun();
      if (scenario.isFailed()) {
        scenario.write(WebDriverHelper.getWebDriver().getCurrentUrl());
        byte[] screenShot = ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.BYTES);
        scenario.embed(screenShot, "image/png");
      }

    } catch (WebDriverException | ClassCastException wde) {
      LOG.error(wde.getMessage());
    } finally {
      getWebDriver().switchTo().defaultContent();

    }

  }
}
