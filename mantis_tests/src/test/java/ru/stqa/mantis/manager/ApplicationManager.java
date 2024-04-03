package ru.stqa.mantis.manager;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Properties;

public class ApplicationManager {
  public WebDriver driver;
  private String browser;
  private Properties properties;
  private SessionHelper sessionHelper;
  private HttpSessionHelper httpsessionHelper;
  private JamesClickHelper jamesCli;
  private MailHelper mailHelper;

  public void init(String browser, Properties properties) {
    this.browser = browser;
    this.properties = properties;

  }

  public WebDriver driver() {

    if (driver == null) {
      if ("firefox".equals(browser)) {
        driver = new FirefoxDriver();
      } else if ("chrome".equals(browser)) {
        driver = new ChromeDriver();
      } else {
        throw new IllegalArgumentException(String.format("Unknown browser %s", browser));
      }
      Runtime.getRuntime().addShutdownHook(new Thread(driver::quit));
      driver.get(properties.getProperty("web.baseUrl"));
      driver.manage().window().setSize(new Dimension(1082, 824));
      //session().login(properties.getProperty("web.userName"),properties.getProperty("web.password"));
    }
    return driver;
  }

  public SessionHelper session() {
    if (sessionHelper == null) {
      sessionHelper = new SessionHelper(this);
    }
    return sessionHelper;
  }

  public HttpSessionHelper http() {
    if (httpsessionHelper == null) {
      httpsessionHelper = new HttpSessionHelper(this);
    }
    return httpsessionHelper;
  }

  public JamesClickHelper jamesCli() {
    if (jamesCli == null) {
      jamesCli = new JamesClickHelper(this);
    }
    return jamesCli;
  }

  public MailHelper mail() {
    if (mailHelper == null) {
      mailHelper = new MailHelper(this);
    }
    return mailHelper;
  }

  public String property(String name) {
    return properties.getProperty(name);
  }
}


