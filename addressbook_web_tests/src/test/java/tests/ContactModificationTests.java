package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

import static manager.HelperBase.randomFile;

public class ContactModificationTests extends TestBase {
  @Test
  void canModifyContact() {
    if (app.hbm().getContactsCountHbm() == 0) {
      app.hbm().createContactsHbm(new ContactData("","ghbdt","s","dfsfds","dfsfds","fdsfs","fdssdsf"));
      app.driver.navigate().refresh();
    }
    var oldContacts = app.hbm().getContactsListHbm();
    //var oldContacts = app.contacts().getContactsList();
    var rnd = new Random();
    var index = rnd.nextInt(oldContacts.size());
    var testData = new ContactData().withLastName("modified lastName");
    app.contacts().modifyContact(oldContacts.get(index), testData);
    var newContacts = app.hbm().getContactsListHbm();
    //var newContacts = app.contacts().getContactsList();
    var expectedList = new ArrayList<>(oldContacts);
    expectedList.set(index, testData.withId(oldContacts.get(index).id()));
    Comparator<ContactData> compareById = (o1, o2) -> {
      return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
    };
    newContacts.sort(compareById);
    expectedList.sort(compareById);
    //Assertions.assertEquals(newContacts, expectedList);
  }
}
