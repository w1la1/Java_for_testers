package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ContactRemovalTests extends TestBase {
    @Test
    public void canRemoveContact() {
        if (app.contacts().getContactsCount() == 0) {
            app.contacts().createContact(new ContactData("java_for_testers", "header", "footer", "sd", "8855"));
        }
        app.contacts().removeContact();
    }
    @Test
    public void canRemoveAllContacts() {
        if (app.contacts().getContactsCount() == 0) {
            app.contacts().createContact(new ContactData("java_for_testers", "header", "footer", "sd", "8855"));
        }
        app.contacts().removeAllContacts();
        Assertions.assertEquals(0,app.contacts().getContactsCount());
    }


}
