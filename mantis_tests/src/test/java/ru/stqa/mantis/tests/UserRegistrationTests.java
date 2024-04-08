package ru.stqa.mantis.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.mantis.common.CommonFunctions;
import ru.stqa.mantis.model.DeveloperMailUser;
import ru.stqa.mantis.model.UserData;

import java.time.Duration;

public class UserRegistrationTests extends TestBase {
  DeveloperMailUser user;

  @Test
  void canRegisterUser() throws InterruptedException {
    var username = CommonFunctions.randomString(8);

    var password = CommonFunctions.randomString(4);
    var email = String.format("%s@localhost", username);
    //создать (адрес) на почтововом сервере(JamesHelper)
    app.jamesApi().addUser(email, password);
    //заполняем форму создания и отправляем(браузер)
    app.session().signUpForANewAccount(username, email);

    //ждем почту (MailHelper)
    var messages = app.mail().receive(email, password, Duration.ofSeconds(60));
    Assertions.assertEquals(1, messages.size());
    System.out.println(messages);
    // звлекаем ссылку из письма
    var url = app.mail().extractUrl(messages.toString());
    //проходим по ссылке и завершаем регистрацию(браузер)
    app.session().openPage(url);
    app.session().endOfRegistration(username, password);
    //проверяем, что пользователь может залогиниться (HttpSessionHelper)
    app.http().login(username, password);
    Assertions.assertTrue(app.http().isLoggedIn());
  }

  @Test
  void canCreateUser2() throws InterruptedException {

    var password = CommonFunctions.randomString(4);
    user = app.developerMail().addUser();
    var email = String.format("%s@developermail.com", user.name());

    //создать (адрес) на почтововом сервере(JamesHelper)
//    app.jamesApi().addUser(email, password);
    //заполняем форму создания и отправляем(браузер)
    app.session().signUpForANewAccount(user.name(), email);

    //ждем почту
    var messages = app.developerMail().receive(user, Duration.ofSeconds(10));

    // звлекаем ссылку из письма
    var url = app.mail().extractUrl(messages);

    //проходим по ссылке и завершаем регистрацию(браузер)
    app.session().openPage(url);
    app.session().endOfRegistration(user.name(), password);

    //проверяем, что пользователь может залогиниться (HttpSessionHelper)

    app.http().login(user.name(), password);
    Assertions.assertTrue(app.http().isLoggedIn());
  }

  @AfterEach
  void deleteMailUser() {
    app.developerMail().deleteUser(user);
  }

}
