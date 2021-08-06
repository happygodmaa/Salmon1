package com.staples.test.framework.helpers;

import java.net.MalformedURLException;
import java.net.URL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UrlBuilder {
  private static final Logger LOG = LoggerFactory.getLogger(UrlBuilder.class);
  private static final String RUN_CONFIG_PROPERTIES = "/environment.properties";
  private static URL basePath;
  private static URL backofficeUrl;

  static {
    try {
      PropertyReader.loadRunConfigProps(RUN_CONFIG_PROPERTIES);
      System.out.println("static started");
      basePath = new URL(PropertyReader.getProp("site.url"));
      backofficeUrl = new URL(PropertyReader.getProp("backoffice.url"));

    } catch (MalformedURLException e) {
      LOG.error(e.getMessage());
    }
  }

  public static void startAtHomePage() {
    WebDriverHelper.getWebDriver().navigate().to((basePath));

  }

  public static void OpenBackofficeUrl() {
    LOG.info("Backoffice url is : " + backofficeUrl);
    WebDriverHelper.getWebDriver().navigate().to(backofficeUrl);
  }

  public static String getUrl(String applicationUrl) {
    return PropertyReader.getProp(applicationUrl);
  }
}
