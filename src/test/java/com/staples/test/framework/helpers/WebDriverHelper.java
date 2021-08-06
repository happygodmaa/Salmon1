package com.staples.test.framework.helpers;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Platform;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebDriverHelper extends EventFiringWebDriver {
  private static final Logger LOG = LoggerFactory.getLogger(WebDriverHelper.class);
  // private static RemoteWebDriver REAL_DRIVER = null;
  public static RemoteWebDriver REAL_DRIVER = null;
  // String s="ram"{
  // System.out.println("hi");
  // };
  private static final Thread CLOSE_THREAD = new Thread() {
    @Override
    public void run() {
      REAL_DRIVER.quit();
    }
  };
  private static String BROWSER;
  private static String PLATFORM;
  private static String DRIVER_PATH;
  private static String FILE_SEPARATOR;
  private static String SELENIUM_HOST;
  private static String SELENIUM_PORT;
  private static String SELENIUM_REMOTE_URL;
  private static Dimension BROWSER_WINDOW_SIZE;
  private static Integer BROWSER_WINDOW_WIDTH;
  private static Integer BROWSER_WINDOW_HEIGHT;
  static Proxy proxyObj;

  static {
    PropertyReader.loadRunConfigProps("/environment.properties");
    // environment.properties--available in src/main/resources
    // profile.path=${profile.path}
    SELENIUM_HOST = System.getProperty("driverhost");
    System.out.println("my sel host----" + SELENIUM_HOST);
    SELENIUM_PORT = System.getProperty("driverport");
    FILE_SEPARATOR = System.getProperty("file.separator");
    PLATFORM = PropertyReader.getProp("platform");
    BROWSER = PropertyReader.getProp("browser");
    BROWSER_WINDOW_WIDTH = Integer.parseInt(PropertyReader.getProp("browser.width"));
    BROWSER_WINDOW_HEIGHT = Integer.parseInt(PropertyReader.getProp("browser.height"));
    BROWSER_WINDOW_SIZE = new Dimension(BROWSER_WINDOW_WIDTH, BROWSER_WINDOW_HEIGHT);

    System.setProperty("webdriver.gecko.driver", getDriverPath());
    System.out.println("my driver path-----" + getDriverPath());
    System.setProperty("webdriver.chrome.driver", getDriverPath());
    System.setProperty("phantomjs.binary.path", getDriverPath());

    try {
      switch (BROWSER.toLowerCase()) {
        case ("chrome"):
          startChromeDriver();
          break;
        case ("firefox"):
          startFireFoxDriver();
          break;
        case ("phantomjs"):
          startPhantomJsDriver();
          break;
        case ("sauce"):
          startSauceDriver();
          break;
        default:
          throw new IllegalArgumentException(
              "Browser " + BROWSER + " or Platform " + PLATFORM + " type not supported");
      }
    } catch (IllegalStateException e) {
      LOG.error(" Browser parameter: " + BROWSER + " , Platform parameter: " + PLATFORM
          + " type not supported");
    }
    Runtime.getRuntime().addShutdownHook(CLOSE_THREAD);
  }

  private WebDriverHelper() {
    super(REAL_DRIVER);
  }

  public static WebDriver getWebDriver() {
    // nikhil added implicitlywait on 6-12-20
    WebDriverHelper.REAL_DRIVER.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    return REAL_DRIVER;
  }

  private static String getDriverPath() {
    if (BROWSER.equals("firefox") && PLATFORM.contains("win")) {
      DRIVER_PATH = "tools" + FILE_SEPARATOR + "geckodriver" + FILE_SEPARATOR + PLATFORM
          + FILE_SEPARATOR + "geckodriver.exe";
    } else if (BROWSER.equals("chrome") && PLATFORM.contains("win")) {
      DRIVER_PATH = "tools" + FILE_SEPARATOR + "chromedriver" + FILE_SEPARATOR + PLATFORM
          + FILE_SEPARATOR + "chromedriver.exe";
    } else if (BROWSER.equals("chrome") && PLATFORM.contains("linux")) {
      DRIVER_PATH = "tools" + FILE_SEPARATOR + "chromedriver" + FILE_SEPARATOR + PLATFORM
          + FILE_SEPARATOR + "chromedriver";
      System.out.println(DRIVER_PATH);

    } else if (BROWSER.equals("phantomjs") && PLATFORM.contains("linux")) {
      DRIVER_PATH = "tools" + FILE_SEPARATOR + "phantomjs" + FILE_SEPARATOR + PLATFORM
          + FILE_SEPARATOR + "phantomjs";
    } else if (BROWSER.equals("phantomjs") && PLATFORM.contains("win")) {
      DRIVER_PATH = "tools" + FILE_SEPARATOR + "phantomjs" + FILE_SEPARATOR + PLATFORM
          + FILE_SEPARATOR + "phantomjs.exe";
    }
    return DRIVER_PATH;
  }

  private static void startFireFoxDriver() {
    DesiredCapabilities capabilities = getFireFoxDesiredCapabilities();
    if (SELENIUM_HOST == null)
      REAL_DRIVER = new FirefoxDriver();
    else {
      try {
        // Changed according to staples grid OS setup
        capabilities.setPlatform(Platform.WINDOWS);
        REAL_DRIVER = getRemoteWebDriver(capabilities);
        REAL_DRIVER.setFileDetector(new LocalFileDetector());
      } catch (MalformedURLException e) {
        LOG.error(SELENIUM_REMOTE_URL + " Error " + e.getMessage());
      }
    }
    REAL_DRIVER.manage().window().setSize(BROWSER_WINDOW_SIZE);
  }

  private static void startPhantomJsDriver() {
    DesiredCapabilities capabilities = getPhantomJsCapabilities();
    if (SELENIUM_HOST == null)
      REAL_DRIVER = new PhantomJSDriver(capabilities);
    else {
      try {
        REAL_DRIVER = getRemoteWebDriver(capabilities);
      } catch (MalformedURLException e) {
        LOG.error(SELENIUM_REMOTE_URL + " Error " + e.getMessage());
      }
    }
    REAL_DRIVER.manage().window().setSize(BROWSER_WINDOW_SIZE);
  }

  private static void startSauceDriver() {
    DesiredCapabilities capabilities = getSauceCapabilities();
    try {
      REAL_DRIVER = new RemoteWebDriver(
          new URL("http://username-string:access-key-string@ondemand.saucelabs.com:80/wd/hub"),
          capabilities);
    } catch (MalformedURLException e) {
      LOG.error(" Error Sauce Url " + e.getMessage());
    }
  }

  private static void startChromeDriver() {
    DesiredCapabilities capabilities = getChromeDesiredCapabilities();

    if (SELENIUM_HOST == null) {
      REAL_DRIVER = new ChromeDriver(ChromeDriverService.createDefaultService(), capabilities);
    } else {
      try {
        REAL_DRIVER = getRemoteWebDriver(capabilities);
        REAL_DRIVER.setFileDetector(new LocalFileDetector());
      } catch (MalformedURLException e) {
        LOG.error(SELENIUM_REMOTE_URL + " Error " + e.getMessage());
      }
    }
    REAL_DRIVER.manage().window().setSize(BROWSER_WINDOW_SIZE);
  }

  private static DesiredCapabilities getChromeDesiredCapabilities() {

    LoggingPreferences logs = new LoggingPreferences();
    logs.enable(LogType.DRIVER, Level.OFF);
    DesiredCapabilities capabilities = DesiredCapabilities.chrome();
    capabilities.setCapability(CapabilityType.LOGGING_PREFS, logs);

    ChromeOptions chromeOptions = new ChromeOptions();
    chromeOptions.addArguments("--disable-web-security");
    chromeOptions.addArguments("--test-type");
    capabilities.setCapability("chrome.verbose", false);
    capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
    return capabilities;
  }

  private static DesiredCapabilities getFireFoxDesiredCapabilities() {
    DesiredCapabilities capabilities = new DesiredCapabilities();
    FirefoxProfile profile = new FirefoxProfile();
    profile.setPreference("dom.file.createInChild", true);
    capabilities.setCapability("marionette", true);
    capabilities.setCapability("acceptInsecureCerts", true);
    capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
    capabilities.setBrowserName("firefox");
    capabilities.setCapability("disable-restore-session-state", true);
    capabilities.setCapability(FirefoxDriver.PROFILE, profile);
    return capabilities;
  }

  private static DesiredCapabilities getPhantomJsCapabilities() {
    LoggingPreferences logs = new LoggingPreferences();
    logs.enable(LogType.DRIVER, Level.OFF);
    DesiredCapabilities capabilities = DesiredCapabilities.phantomjs();
    capabilities.setCapability(CapabilityType.LOGGING_PREFS, logs);
    capabilities.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
        getDriverPath());
    return capabilities;
  }

  private static DesiredCapabilities getSauceCapabilities() {
    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setCapability("browserName", BROWSER);
    capabilities.setCapability("platform", PLATFORM);
    capabilities.setCapability("sauce-advisor", false);
    capabilities.setCapability("record-video", false);
    capabilities.setCapability("record-screenshots", false);
    return capabilities;
  }

  private static RemoteWebDriver getRemoteWebDriver(DesiredCapabilities capabilities)
      throws MalformedURLException {
    SELENIUM_REMOTE_URL = "http://" + SELENIUM_HOST + ":" + SELENIUM_PORT + "/wd/hub";
    LOG.info(SELENIUM_REMOTE_URL + " Checking Selenium Remote URL");
    return new RemoteWebDriver(new URL(SELENIUM_REMOTE_URL), (capabilities));
  }

  @Override
  public void close() {
    if (Thread.currentThread() != CLOSE_THREAD) {
      throw new UnsupportedOperationException(
          "You shouldn't close this WebDriver. It's shared and will close when the JVM exits.");
    }
    super.close();
  }

  public static void setProxy() {
    String proxy = "http://127.0.0.1:8888";
    proxyObj = new Proxy();
    proxyObj.setHttpProxy(proxy).setSslProxy(proxy).setSocksProxy(proxy);
    LOG.info("Configured proxy with port 8888");
  }

}
