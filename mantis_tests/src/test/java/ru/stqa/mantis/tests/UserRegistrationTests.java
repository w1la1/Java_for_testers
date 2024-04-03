package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.mantis.common.CommonFunctions;

import java.time.Duration;
import java.util.regex.Pattern;

public class UserRegistrationTests extends TestBase {
  @Test
  void canRegisterUser() throws InterruptedException {
    var username = CommonFunctions.randomString(8);
    var password = CommonFunctions.randomString(4);
    var email = String.format("%s@localhost", username);
    //создать (адрес) на почтововом сервере(JamesHelper)
    app.jamesCli().addUser(email, password);
    //заполняем форму создания и отправляем(браузер)
    app.session().signUpForANewAccount(username, email);

    //ждем почту (MailHelper)
    var messages = app.mail().receive(email, password, Duration.ofSeconds(60));
    Assertions.assertEquals(1, messages.size());
    System.out.println(messages);
    // звлекаем ссылку из письма
    var text = messages.get(0).content();
    var pattern = Pattern.compile("http://\\S*");
    var matcher = pattern.matcher(text);
    var url = "";
    if (matcher.find()) {
      url = text.substring(matcher.start(), matcher.end());
      //System.out.println(url);
    }
    //проходим по ссылке и завершаем регистрацию(браузер)
    app.session().openPage(url);
    app.session().endOfRegistration(username, password);
    //проверяем, что пользователь может залогиниться (HttpSessionHelper)
    app.http().login(username, password);
    Assertions.assertTrue(app.http().isLoggedIn());
  }

  @Test
  void can() {
    app.session().signUpForANewAccount("user1", "user1@localhost");
  }
}
