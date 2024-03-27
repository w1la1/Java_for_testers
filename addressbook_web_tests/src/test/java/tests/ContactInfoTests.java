package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ContactInfoTests extends TestBase {
  @Test
  void testPhonesAddressesEmails() {
    if (app.hbm().getContactsCountHbm() == 0) {
      app.hbm().createContactsHbm(new ContactData("", "ghbdt", "dfsfds", "", "dfsfds", "fdsfs", "", "", "888", "999", "1", ""));
      refreshPage();
    }
    var contacts = app.hbm().getContactsListHbm();
    var expected = contacts.stream().collect(Collectors.toMap(ContactData::id, contact ->
        Stream.of(contact.home(), contact.mobile(), contact.work(), contact.secondary())
            .filter(s -> s != null && !"".equals(s))
            .collect(Collectors.joining("\n"))
    ));
    var phones = app.contacts().getPhones();
    Assertions.assertEquals(expected, phones);

    expected = contacts.stream().collect(Collectors.toMap(ContactData::id, contact ->
        Stream.of(contact.address())
            .filter(s -> s != null && !"".equals(s))
            .collect(Collectors.joining("\n"))
    ));
    var address = app.contacts().getAddress();
    Assertions.assertEquals(expected, address);

    expected = contacts.stream().collect(Collectors.toMap(ContactData::id, contact ->
        Stream.of(contact.eMail(), contact.eMail2(), contact.eMail3())
            .filter(s -> s != null && !"".equals(s))
            .collect(Collectors.joining("\n"))
    ));
    var emails = app.contacts().getEmails();
    Assertions.assertEquals(expected, emails);
  }

  @Test
  void testAddress() {
    if (app.hbm().getContactsCountHbm() == 0) {
      app.hbm().createContactsHbm(new ContactData("", "ghbdt", "dfsfds", "", "Walking Street", "fdsfs@ya.ru", "example@mail.ru", "email@rambler.ru", "888", "999", "1", ""));
    }
    var contacts = app.hbm().getContactsListHbm();
    var expected = contacts.stream().collect(Collectors.toMap(ContactData::id, contact ->
        Stream.of(contact.address())
            .filter(s -> s != null && !"".equals(s))
            .collect(Collectors.joining("\n"))
    ));
    var address = app.contacts().getAddress();
    Assertions.assertEquals(expected, address);
  }

  @Test
  void testEmails() {
    if (app.hbm().getContactsCountHbm() == 0) {
      app.hbm().createContactsHbm(new ContactData("", "ghbdt", "dfsfds", "", "Walking Street", "fdsfs@ya.ru", "example@mail.ru", "email@rambler.ru", "888", "999", "1", ""));
    }
    var contacts = app.hbm().getContactsListHbm();
    var expected = contacts.stream().collect(Collectors.toMap(ContactData::id, contact ->
        Stream.of(contact.eMail(), contact.eMail2(), contact.eMail3())
            .filter(s -> s != null && !"".equals(s))
            .collect(Collectors.joining("\n"))
    ));
    var emails = app.contacts().getEmails();
    Assertions.assertEquals(expected, emails);
  }
}

