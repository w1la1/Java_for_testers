package tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.CommonFunctions;
import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static manager.HelperBase.randomFile;

public class ContactCreationTests extends TestBase {
  public static List<ContactData> contactProvider() throws IOException {
    var result = new ArrayList<ContactData>();
//    for (var lastname : List.of("", "contact lastname")) {
//      for (var firstname : List.of("", "contact firstname")) {
//        result.add(new ContactData()
//            .withLastName(lastname)
//            .withFirstName(firstname).withPhoto(randomFile("src/test/resources/images")));
//      }
//    }
//    for (int i = 0; i < 5; i++) {
//      result.add(new ContactData()
//          .withLastName(CommonFunctions.randomString(i * 10))
//          .withFirstName(CommonFunctions.randomString(i * 10))
//          .withAddress(CommonFunctions.randomString(i * 10))
//          .withEmail(CommonFunctions.randomString(i * 10))
//          .withPhone(CommonFunctions.randomString(i * 10))
//          .withPhoto(randomFile("src/test/resources/images")));
//    }
//    return result;
    var json = Files.readString(Paths.get("contacts.json"));
    ObjectMapper mapper = new ObjectMapper();
    var value = mapper.readValue(json, new TypeReference<List<ContactData>>() {
    });
    result.addAll(value);
    return result;
  }
  public static List<ContactData> singleRandomContact() throws IOException {
    return List.of(new ContactData()
        .withLastName(CommonFunctions.randomString(10))
        .withFirstName(CommonFunctions.randomString(15))
        .withAddress(CommonFunctions.randomString(10))
        .withEmail(CommonFunctions.randomString(15))
        .withHome(CommonFunctions.randomString(10)));
  }

  public static List<ContactData> negativeContactProvider() {
    var result = new ArrayList<ContactData>(List.of(new ContactData("","", "contact last name '", "contact firstname", "", "", "", "", "", "", "", "")));
    return result;
  }

  @ParameterizedTest
  @MethodSource("singleRandomContact")
  public void canCreateContacts(ContactData contact) {
    var oldContacts = app.hbm().getContactsListHbm();
    //var oldContacts = app.contacts().getContactsList();
    app.contacts().createContact(contact);
    var newContacts = app.hbm().getContactsListHbm();
    //var newContacts = app.contacts().getContactsList();
    Comparator<ContactData> compareById = (o1, o2) -> {
      return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
    };
    newContacts.sort(compareById);
    var expectedList = new ArrayList<>(oldContacts);
    expectedList.add(contact.withId(newContacts.get(newContacts.size() - 1).id()));
    expectedList.sort(compareById);
    Assertions.assertEquals(newContacts, expectedList);
  }

  @ParameterizedTest
  @MethodSource("negativeContactProvider")
  public void cantCreateContacts(ContactData contact) {
    var oldContacts = app.contacts().getContactsList();
    app.contacts().createContact(contact);
    var newContacts = app.contacts().getContactsList();
    Assertions.assertEquals(newContacts, oldContacts);
  }

  @Test
  public void CanCreateContactsWithPhoto() {
    var contact = new ContactData()
        .withFirstName(CommonFunctions.randomString(10))
        .withLastName(CommonFunctions.randomString(10))
        .withPhoto(randomFile("src/test/resources/images"));
    app.contacts().createContact(contact);
  }
  @Test
  public void CanCreateContactInGroup() {
    var contact = new ContactData()
        .withFirstName(CommonFunctions.randomString(10))
        .withLastName(CommonFunctions.randomString(10));
    if (app.hbm().getGroupsCountHbm() == 0) {
      app.hbm().createGroupHbm(new GroupData("", "java_for_testers", "header", "footer"));}
    var group = app.hbm().getGroupsListHbm().get(0);
    var oldRelated = app.hbm().getContactsInGroup(group);
    app.contacts().createContactInGroup(contact,group);
    var newRelated = app.hbm().getContactsInGroup(group);
    Assertions.assertEquals(oldRelated.size()+1,newRelated.size());
  }
  @Test
  public void canCreateNewContactAndPlaceItInGroup(){
    var contact = new ContactData()
        .withFirstName(CommonFunctions.randomString(10))
        .withLastName(CommonFunctions.randomString(10));
    app.hbm().createContactsHbm(contact);
    app.driver.navigate().refresh();
    if (app.hbm().getGroupsCountHbm() == 0) {
      app.hbm().createGroupHbm(new GroupData("", "java_for_testers", "header", "footer"));
      app.driver.navigate().refresh();}
    var group = app.hbm().getGroupsListHbm().get(0);
    var oldRelated = app.hbm().getContactsInGroup(group);
    app.driver.navigate().refresh();
    app.contacts().replaceContactIntoGroup(contact,group);
    var newRelated = app.hbm().getContactsInGroup(group);
    Assertions.assertEquals(oldRelated.size()+1,newRelated.size());
  }
}


