package manager;

import model.ContactData;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {
    public ContactHelper(ApplicationManager manager) {
        super(manager);
    }

    public void openContactPage() {
        if (!manager.isElementPresent(By.xpath("//input[@value='Send e-Mail']"))) {
            click(By.linkText("home"));
        }
    }

    public boolean isContactPresent() {
        openContactPage();
        return manager.isElementPresent(By.name("selected[]"));
    }

    public void createContact(ContactData contact) {
        initContactCreation();
        fillContactForm(contact);
        submitContactCreation();
        returnToContactPage();
    }

    public void removeContact(ContactData contact) {
        openContactPage();
        selectContact(contact);
        removeSelectedContact();
        returnToContactPage();
    }

    private void removeSelectedContact() {
        click(By.xpath("//input[@value=\'Delete\']"));
    }

    private void selectContact(ContactData contact) {
        click(By.cssSelector(String.format("input[value='%s']", contact.id())));
    }

    private void returnToContactPage() {
        click(By.linkText("home"));
    }

    private void submitContactCreation() {
        click(By.name("submit"));
    }

    private void fillContactForm(ContactData contact) {
        type(By.name("lastname"), contact.lastName());
        type(By.name("firstname"), contact.firstName());
        type(By.name("address"), contact.address());
        type(By.name("email"), contact.eMail());
        type(By.name("home"), contact.phone());
    }

    private void initContactCreation() {
        click(By.linkText("add new"));
    }

    public int getContactsCount() {
        openContactPage();
        return manager.driver.findElements(By.name("selected[]")).size();
    }

    public void removeAllContacts() {
        selectAllContact();
        removeSelectedContact();
    }

    private void selectAllContact() {
        var checkboxes = manager.driver.findElements(By.name("selected[]"));
        for (var checkbox : checkboxes) {
            checkbox.click();
        }
    }

    public void modifyContact(ContactData contact, ContactData modifiedContact) {
        openContactPage();
        selectContact(contact);
        initContactModification();
        fillContactForm(modifiedContact);
        submitContactModification();
        returnToContactPage();
    }

    private void submitContactModification() {
        click(By.name("update"));
    }

    private void initContactModification() {
        click(By.xpath("//img[@alt='Edit']"));
    }

    public List<ContactData> getContactsList() {
        openContactPage();
        var contacts = new ArrayList<ContactData>();
       // var tds = manager.driver.findElements(By.xpath("//*[@id=\"maintable\"]/tbody/tr[2]/td[1]"));td.center:nth-child(1)
        var tds = manager.driver.findElements(By.cssSelector("td.center:nth-child(1)"));//#maintable > tbody > tr:nth-child(2) > td:nth-child(1)
        for (var td : tds) {
            var lastname = td.getText();
            var checkbox = td.findElement(By.name("selected[]"));
            var id = checkbox.getAttribute("value");
            contacts.add(new ContactData().withId(id).withLastName(lastname));
        }
        return contacts;
    }

}
