package ru.stqa.mantis.manager;

import org.openqa.selenium.By;

import java.io.File;
import java.nio.file.Paths;
import java.util.Random;

public class HelperBase {
  protected final ApplicationManager manager;

  public HelperBase(ApplicationManager manager) {
    this.manager = manager;
  }

  protected void type(By locator, String text) {
    click(locator);
    manager.driver().findElement(locator).clear();
    manager.driver().findElement(locator).sendKeys(text);
  }

  public void click(By locator) {
    manager.driver().findElement(locator).click();
  }

  protected boolean isElementPresent(By locator) {
    return manager.driver().findElements(locator).size() > 0;
  }
}
