package tests;

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
      app.hbm().createContactsHbm(new ContactData("", "ghbdt", "dfgh", "dfsfds", "dfsfds", "fdsfs", "", "", "fdssdsf", "", "", ""));
      refreshPage();
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
      app.hbm().createContactsHbm(new ContactData("", "ghbdt", "dfsfds", "", "dfsfds", "fdsfs", "", "", "fdssdsf", "", "", ""));
      // app.contacts.click(By.linkText("home"));
      refreshPage();
    }
    app.contacts().removeAllContacts();
    Assertions.assertEquals(0, app.hbm().getContactsCountHbm());
  }

  @Test
  public void CanRemoveContactInGroup() {
    if (app.hbm().getContactsCountHbm() == 0) {
      app.hbm().createContactsHbm(new ContactData("", "ghbdt", "dfsfds", "", "dfsfds", "fdsfs", "", "", "fdssdsf", "", "", ""));
    }
    if (app.hbm().getGroupsCountHbm() == 0) {
      app.hbm().createGroupHbm(new GroupData("", "java_for_testers", "header", "footer"));
    }
    var contact = app.hbm().getContactsListHbm().get(0);
    var group = app.hbm().getGroupsListHbm().get(0);
    if (app.hbm().getContactsInGroup(group).isEmpty()) {
      refreshPage();
      app.contacts().replaceContactIntoGroup(contact, group);
    }
    var oldRelated = app.hbm().getContactsInGroup(group);
    refreshPage();
    app.contacts().removeContactInGroup(contact, group);
    var newRelated = app.hbm().getContactsInGroup(group);
    Assertions.assertEquals(oldRelated.size() - 1, newRelated.size());
  }
}



