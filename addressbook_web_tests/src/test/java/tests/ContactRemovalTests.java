package tests;

import common.CommonFunctions;
import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

public class ContactRemovalTests extends TestBase {
  @Test
  public void canRemoveContact() {
    if (app.hbm().getContactsCountHbm() == 0) {
      app.hbm().createContactsHbm(new ContactData("", "ghbdt", "dfgh", "dfsfds", "dfsfds", "fdsfs", "fdssdsf"));
      app.driver.navigate().refresh();
    }
    var oldContacts = app.hbm().getContactsListHbm();
    //var oldContacts = app.contacts().getContactsList();
    var rnd = new Random();
    var index = rnd.nextInt(oldContacts.size());
    app.contacts().removeContact(oldContacts.get(index));
    var newContacts = app.hbm().getContactsListHbm();
    //var newContacts = app.contacts().getContactsList();
    var expectedList = new ArrayList<>(oldContacts);
    expectedList.remove(index);
    Assertions.assertEquals(newContacts, expectedList);
  }


  @Test
  public void canRemoveAllContacts() {
//    if (app.contacts().getContactsCount() == 0) {
//      app.contacts().createContact(new ContactData().withLastName("java_for_testers").withFirstName("kfjgsdklfj"));
//    }
    if (app.hbm().getContactsCountHbm() == 0) {
      app.hbm().createContactsHbm(new ContactData("", "ghbdt", "dfsfds", "", "dfsfds", "fdsfs", "fdssdsf"));
      // app.contacts.click(By.linkText("home"));
      app.driver.navigate().refresh();
    }
    app.contacts().removeAllContacts();
    Assertions.assertEquals(0, app.hbm().getContactsCountHbm());
  }

  @Test
  public void CanRemoveContactInGroup() {
    var contact = new ContactData()
        .withFirstName(CommonFunctions.randomString(10))
        .withLastName(CommonFunctions.randomString(10));
    if (app.hbm().getGroupsCountHbm() == 0) {
      app.hbm().createGroupHbm(new GroupData("", "java_for_testers", "header", "footer"));
    }
    var group = app.hbm().getGroupsListHbm().get(0);
    if (app.hbm().getContactsInGroup(group).isEmpty()) {
      app.contacts().createContactInGroup(contact, group);
    }
    var oldRelated = app.hbm().getContactsInGroup(group);
   // app.contacts().createContactInGroup(contact, group);
    app.contacts().removeContactInGroup(contact, group);
    var newRelated = app.hbm().getContactsInGroup(group);
    Assertions.assertEquals(oldRelated.size() -1 , newRelated.size());
  }
}



