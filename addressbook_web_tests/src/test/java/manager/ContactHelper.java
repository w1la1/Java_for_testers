package manager;

import model.ContactData;
import org.openqa.selenium.By;

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

    public void removeContact() {
        openContactPage();
        selectContact();
        removeSelectedContact();
        returnToContactPage();
    }

    private void removeSelectedContact() {
        click(By.xpath("//input[@value=\'Delete\']"));
    }

    private void selectContact() {
        click(By.name("selected[]"));
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
}
