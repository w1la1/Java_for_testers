package manager;

import io.qameta.allure.Step;
import model.ContactData;
import model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
@Step
  public void createContactInGroup(ContactData contact, GroupData group) {
    initContactCreation();
    fillContactForm(contact);
    selectGroup(group);
    submitContactCreation();
    returnToContactPage();
  }

  public void replaceContactIntoGroup(ContactData contact, GroupData group) {
    selectContact(contact);
    selectGroupForReplaceContact(group);
    addContactToGroup();
    returnToContactPage();
  }
  private void addContactToGroup() {
    click(By.name("add"));
  }

  public void removeContactInGroup(ContactData contact, GroupData group) {
    openContactPage();
    selectGroupInContactPage(group);
    selectContact(contact);
    removeContactFromGroup();
    returnToContactPage();
  }

  private void removeContactFromGroup() {
    click(By.name("remove"));
  }

  private void selectGroup(GroupData group) {
    new Select(manager.driver.findElement(By.name("new_group"))).selectByValue(group.id());
  }

  private void selectGroupForReplaceContact(GroupData group) {
    new Select(manager.driver.findElement(By.name("to_group"))).selectByValue(group.id());
  }

  private void selectGroupInContactPage(GroupData group) {
    new Select(manager.driver.findElement(By.name("group"))).selectByValue(group.id());
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

  private void selectContactForRemoveFromGroup(ContactData contact) {
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
    type(By.name("home"), contact.home());
    // attach(By.name("photo"), contact.photo());
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
    initContactModification(contact);
    fillContactForm(modifiedContact);
    submitContactModification();
    returnToContactPage();
  }

  private void submitContactModification() {
    click(By.name("update"));
  }

  private void initContactModification(ContactData contact) {
    click(By.xpath(String.format("//a[@href='edit.php?id=%s']", contact.id())));
  }

  public List<ContactData> getContactsList() {
    var contacts = new ArrayList<ContactData>();
    // var tds = manager.driver.findElements(By.xpath("//*[@id=\"maintable\"]/tbody/tr[2]/td[1]"));td.center:nth-child(1)
    var tr = manager.driver.findElements(By.name("entry"));//#maintable > tbody > tr:nth-child(2) > td:nth-child(1)
    for (var td : tr) {
      //var className = td.findElement(By.xpath(".//td"));
      var checkbox = td.findElement(By.name("selected[]"));
      var id = checkbox.getAttribute("value");
      //#maintable > tbody > tr.odd > td:nth-child(2) #maintable > tbody > tr.odd > td:nth-child(3)
      var lastName = td.findElement(By.xpath("./td[2]")).getText();
      var firstName = td.findElement(By.xpath("./td[3]")).getText();
//      var address = td.findElement(By.xpath("./td[4]")).getText();
//      var email = td.findElement(By.xpath("./td[5]")).getText();
//      var phone = td.findElement(By.xpath("./td[6]")).getText();
      //var lastName = className.findElement(By.xpath(String.format("//input[@id='%s']/following-sibling::td[1]", id))).getText();
      // var firstName = className.findElement(By.xpath(String.format("//input[@id='%s']/parent::td/following-sibling::td[2]", id))).getText();
      // var address = className.findElement(By.xpath(String.format("//input[@id='%s']/parent::td/following-sibling::td[3]", id))).getText();
      //  var email = className.findElement(By.xpath(String.format("//input[@id='%s']/parent::td/following-sibling::td[4]", id))).getText();
      // var phone = className.findElement(By.xpath(String.format("//input[@id='%s']/parent::td/following-sibling::td[5]", id))).getText();
      contacts.add(new ContactData().withId(id).withLastName(lastName).withFirstName(firstName));
    }
    return contacts;
  }


  public String getPhones(ContactData contact) {
    return manager.driver.findElement(By.xpath(
        String.format("//input[@id=%s]/../../td[6]", contact.id()))).getText();
  }

  public Map<String, String> getPhones() {
    var result = new HashMap<String,String>();
    List<WebElement> rows = manager.driver.findElements(By.name("entry"));
    for (WebElement row : rows) {
      var id = row.findElement(By.tagName("input")).getAttribute("id");
      var phones = row.findElements(By.tagName("td")).get(5).getText();
      result.put(id,phones);
    }
    return result;
  }
  public Map<String, String> getAddress() {
    var result = new HashMap<String,String>();
    List<WebElement> rows = manager.driver.findElements(By.name("entry"));
    for (WebElement row : rows) {
      var id = row.findElement(By.tagName("input")).getAttribute("id");
      var address = row.findElements(By.tagName("td")).get(3).getText();
      result.put(id,address);
    }
    return result;
  }
  public Map<String, String> getEmails() {
    var result = new HashMap<String,String>();
    List<WebElement> rows = manager.driver.findElements(By.name("entry"));
    for (WebElement row : rows) {
      var id = row.findElement(By.tagName("input")).getAttribute("id");
      var emails = row.findElements(By.tagName("td")).get(4).getText();
      result.put(id,emails);
    }
    return result;
  }
}
