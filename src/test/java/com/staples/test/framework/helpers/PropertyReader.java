package com.staples.test.framework.helpers;

import static java.lang.System.out;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropertyReader {
  private static final Logger LOG = LoggerFactory.getLogger(PropertyReader.class);
  private static Properties environmentProps;
  private static Properties properties;

  // Gets the key from Config.properties related to chosen profile
  // @param key

  public static String getProp(String key) {
    try {
      if ((key == null) || key.isEmpty()) {
        return "";
      } else {
        return properties.getProperty(key).replaceAll("\"", "");
      }
    } catch (NullPointerException ne) {
      LOG.error("Error occurred while extracting value for key: [ " + key + " ]");
      throw ne;
    }
  }

  public static void loadRunConfigProps(String configPropertyFileLocation) {
    environmentProps = new Properties();
    System.out.println("inside propertyreader class---loadrunconfigprop");
    try (InputStream inputStream =
        PropertyReader.class.getResourceAsStream(configPropertyFileLocation)) {
      // configPropertyFileLocation= "/environment.properties"
      environmentProps.load(inputStream);
      environmentProps.list(out);
      System.out.println("environmentProps: " + environmentProps);
    } catch (IOException e) {
      LOG.error(e.getMessage());
    }
    properties = new Properties();
    try (InputStream inputStream =
        PropertyReader.class.getResourceAsStream(environmentProps.getProperty("profile.path"))) {
      properties.load(inputStream);
      properties.list(out);
    } catch (IOException e) {
      LOG.error(e.getMessage());
    }
  }
}
