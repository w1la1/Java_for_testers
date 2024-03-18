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
  @Column(name = "firstname")
  public String firstname;
  @Column(name = "address")
  public String address;
  @Column(name = "email")
  public String email;
  @Column(name = "home")
  public String phone;

  public ContactRecord() {
  }

  public ContactRecord(int id, String lastname, String firstname, String address, String email, String phone) {
    this.id = id;
    this.lastname = lastname;
    this.firstname = firstname;
    this.address = address;
    this.email = email;
    this.phone = phone;
  }
}
