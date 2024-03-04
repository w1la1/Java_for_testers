package tests;

import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {
    public static List<ContactData> contactProvider() {
        var result = new ArrayList<ContactData>(List.of());
        for (var lastname : List.of("", "contact lastname")) {
            for (var firstname : List.of("", "contact firstname")) {
                for (var address : List.of("", " contact address")) {
                    for (var email : List.of("", "contact email")) {
                        for (var phone : List.of("", "contact phone")) {
                            result.add(new ContactData()
                                    .withLastName(lastname)
                                    .withFirstName(firstname)
                                    .withAddress(address)
                                    .withEmail(email)
                                    .withPhone(phone));
                        }
                    }
                }
            }
        }
        for (int i = 0; i < 5; i++) {
            result.add(new ContactData()
                    .withLastName(randomString(i * 10))
                    .withFirstName(randomString(i * 10))
                    .withAddress(randomString(i * 10))
                    .withEmail(randomString(i * 10))
                    .withPhone(randomString(i * 10)));
        }
        return result;
    }

    public static List<ContactData> negativeContactProvider() {
        var result = new ArrayList<ContactData>(List.of(new ContactData("", "contact last name '", "contact firstname", "", "", "")));
        return result;
    }

    @ParameterizedTest
    @MethodSource("contactProvider")
    public void canCreateContacts(ContactData contact) {
        var oldContacts = app.contacts().getContactsList();
        int contactCount = app.contacts().getContactsCount();
        app.contacts().createContact(contact);
        int newContactCount = app.contacts().getContactsCount();
        Assertions.assertEquals(contactCount + 1, newContactCount);
        var newContacts = app.contacts().getContactsList();
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContacts.sort(compareById);
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.add(contact.withId(newContacts.get(newContacts.size() - 1).id()).withLastName("").withFirstName("").withAddress("").withEmail("").withPhone(""));
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

}


