package com.seleniumGrid;

import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

public class TestOnGrid {
  URL url;

  @Test
  public void Test1() {
    DesiredCapabilities ds = DesiredCapabilities.firefox();
    ds.setPlatform(Platform.WINDOWS);
    // URL url = null;
    try {
      // given node or hub IP address--wd-webdriver
      url = new URL("http://192.168.56.1:4444/wd/hub");
    } catch (MalformedURLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    WebDriver driver = new RemoteWebDriver(url, ds);
    driver.get("https://learn-automation.com/");
    System.out.println(driver.getTitle());
    driver.quit();


  }

}
