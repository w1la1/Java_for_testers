package manager;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "addressbook")
public class ContactRecord {
  @Id
  @Column(name = "id")
 public int id;
  @Column(name = "lastname")
  public String lastname;
  @Column(name = "middlename")
  public String middlename;
  @Column(name = "firstname")
  public String firstname;
  @Column(name = "address")
  public String address;
  @Column(name = "email")
  public String email;
  @Column(name = "home")
  public String home;
  public String nickname = "sdasd";
  public String company = "kfdhsfjk";
  public String title = "sdasd";
  public String mobile = "89546";
  public String work = "dsda";
  public String phone2;
  public String fax = "89565";
  public String email2 = "sdas";
  public String email3 = "sdasd";
  public String homepage = "dsds";

  public ContactRecord() {
  }

  public ContactRecord(int id, String lastname,String middlename, String firstname, String address, String email, String phone) {
    this.id = id;
    this.lastname = lastname;
    this.middlename = middlename;
    this.firstname = firstname;
    this.address = address;
    this.email = email;
    this.home = phone;
  }
}
