package ru.stqa.mantis.manager;

import org.openqa.selenium.By;

public class SessionHelper extends HelperBase {
  public SessionHelper(ApplicationManager manager) {
    super(manager);
  }

  void FillFormForANewAccount(String user, String password) {
    type(By.name("username"), user);
    type(By.name("password"), password);
    click(By.cssSelector("input[type='submit']"));
  }


  public void login(String user, String password) {
    type(By.name("username"), user);
    click(By.cssSelector("input[type='submit']"));
    type(By.name("password"), password);
    click(By.cssSelector("input[type='submit']"));
  }

  public void signUpForANewAccount(String user, String email) {
    click(By.xpath("//a[@class=\"back-to-login-link pull-left\"]"));
    type(By.name("username"), user);
    type(By.name("email"), email);
    click(By.cssSelector("input[type='submit']"));
  }
//  public void startCreation(String user,String email) {
//    if (!manager.session().isLoggedIn()) {
//      manager.session().login(manager.property("web.userName"),manager.property("web.password"));
//    }
//    manager.driver().get(String.format());
//  }

  public void openPage(String url) {
    manager.driver.get(url);
  }

  public void endOfRegistration(String realName, String password) {
    type(By.id("realname"), realName);
    type(By.id("password"), password);
    type(By.id("password-confirm"), password);
    click(By.xpath("//button[@type='submit']"));

  }

  public boolean isLoggedIn() {
    return isElementPresent(By.cssSelector("span.user-info"));
  }
}
