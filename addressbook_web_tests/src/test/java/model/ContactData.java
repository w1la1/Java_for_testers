package model;

public record ContactData(String id,
                          String lastName,
                          String middlename,
                          String firstName,
                          String address,
                          String eMail,
                          String eMail2,
                          String eMail3,
                          String home,
                          String mobile,
                          String work,
                          String secondary) {
  public ContactData() {
    this("", "", "", "", "", "", "", "", "", "", "", "");
  }

  public ContactData withId(String id) {
    return new ContactData(id, this.lastName, this.middlename, this.firstName, this.address, this.eMail, this.eMail2,this.eMail3, this.home, this.mobile, this.work, this.secondary);
  }

  public ContactData withLastName(String lastName) {
    return new ContactData(this.id, lastName, this.middlename, this.firstName, this.address, this.eMail, this.eMail2,this.eMail3, this.home, this.mobile, this.work, this.secondary);
  }

  public ContactData withFirstName(String firstName) {
    return new ContactData(this.id, this.lastName, this.middlename, firstName, this.address, this.eMail, this.eMail2,this.eMail3, this.home, this.mobile, this.work, this.secondary);
  }

  public ContactData withAddress(String address) {
    return new ContactData(this.id, this.lastName, this.middlename, this.firstName, address, this.eMail, this.eMail2,this.eMail3, this.home, this.mobile, this.work, this.secondary);
  }

  public ContactData withEmail(String eMail) {
    return new ContactData(this.id, this.lastName, this.middlename, this.firstName, this.address, eMail, this.eMail2,this.eMail3, this.home, this.mobile, this.work, this.secondary);
  }
  public ContactData withEmail2(String eMail2) {
    return new ContactData(this.id, this.lastName, this.middlename, this.firstName, this.address, this.eMail, eMail2,this.eMail3, this.home, this.mobile, this.work, this.secondary);
  }
  public ContactData withEmail3(String eMail3) {
    return new ContactData(this.id, this.lastName, this.middlename, this.firstName, this.address, this.eMail, this.eMail2,eMail3, this.home, this.mobile, this.work, this.secondary);
  }

  public ContactData withHome(String home) {
    return new ContactData(this.id, this.lastName, this.middlename, this.firstName, this.address, this.eMail, this.eMail2,this.eMail3, home, this.mobile, this.work, this.secondary);
  }

      public ContactData withPhoto(String photo) {
        return new ContactData(this.id, this.lastName,this.middlename, this.firstName, this.address, this.eMail, this.eMail2,this.eMail3, this.home, this.mobile, this.work, this.secondary);
    }
  public ContactData withMobile(String mobile) {
    return new ContactData(this.id, this.lastName, this.middlename, this.firstName, this.address, this.eMail, this.eMail2,this.eMail3, this.home, mobile, this.work, this.secondary);
  }

  public ContactData withWork(String work) {
    return new ContactData(this.id, this.lastName, this.middlename, this.firstName, this.address, this.eMail, this.eMail2,this.eMail3, home, this.mobile, work, this.secondary);
  }

  public ContactData withSecondary(String secondary) {
    return new ContactData(this.id, this.lastName, this.middlename, this.firstName, this.address, this.eMail, this.eMail2,this.eMail3, home, this.mobile, this.work, secondary);
  }
}
