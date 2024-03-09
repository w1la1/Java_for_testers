package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

import static manager.HelperBase.randomFile;

public class ContactRemovalTests extends TestBase {
  @Test
  public void canRemoveContact() {
    if (app.contacts().getContactsCount() == 0) {
      app.contacts().createContact(new ContactData().withLastName("java_for_testers").withFirstName("kfjgsdklfj").withPhoto((randomFile("src/test/resources/images"))));
    }
    var oldContacts = app.contacts().getContactsList();
    var rnd = new Random();
    var index = rnd.nextInt(oldContacts.size());
    app.contacts().removeContact(oldContacts.get(index));
    var newContacts = app.contacts().getContactsList();
    var expectedList = new ArrayList<>(oldContacts);
    expectedList.remove(index);
    Assertions.assertEquals(newContacts, expectedList);
  }


  @Test
  public void canRemoveAllContacts() {
    if (app.contacts().getContactsCount() == 0) {
      app.contacts().createContact(new ContactData().withLastName("java_for_testers").withFirstName("kfjgsdklfj").withPhoto((randomFile("src/test/resources/images"))));
    }
    app.contacts().removeAllContacts();
    Assertions.assertEquals(0, app.contacts().getContactsCount());
  }
}



