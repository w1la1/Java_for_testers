package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.mantis.common.CommonFunctions;
import ru.stqa.mantis.model.UserData;

import java.time.Duration;

public class UserCreationTests extends TestBase {
  @Test
  void canCreateUserWithApi() throws InterruptedException {
    //создать (адрес) на почтововом сервере(JamesHelper)
    var username = CommonFunctions.randomString(8);
    var password = CommonFunctions.randomString(4);
    var email = String.format("%s@localhost", username);
    app.jamesApi().addUser(email, password);
    // Сценарий начинает регистрацию нового пользователя в Mantis, используя REST API.
    app.rest().createUser(new UserData(username, password, email));
    //Ждем почту ,извлекаем ссылку,завершаем регистрацию
    var messages = app.mail().receive(email, password, Duration.ofSeconds(10));
    var url = app.mail().extractUrl(messages.toString());
    app.session().openPage(url);
    app.session().endOfRegistration(username, password);
    //проверяем, что пользователь может залогиниться (HttpSessionHelper)
    app.http().login(username, password);
    Assertions.assertTrue(app.http().isLoggedIn());
  }
}
