package model;

public record ContactData(String id, String lastName, String firstName, String address, String eMail, String phone) {
    public ContactData() {
        this("", "", "", "", "", "");
    }

    public ContactData withId(String id) {
        return new ContactData(id, this.lastName, this.firstName, this.address, this.eMail, this.phone);
    }

    public ContactData withLastName(String lastName) {
        return new ContactData(this.id, lastName, this.firstName, this.address, this.eMail, this.phone);
    }

    public ContactData withFirstName(String firstName) {
        return new ContactData(this.id, this.lastName, firstName, this.address, this.eMail, this.phone);
    }

    public ContactData withAddress(String address) {
        return new ContactData(this.id, this.lastName, this.firstName, address, this.eMail, this.phone);
    }

    public ContactData withEmail(String email) {
        return new ContactData(this.id, this.lastName, this.firstName, this.address, eMail, this.phone);
    }

    public ContactData withPhone(String phone) {
        return new ContactData(this.id, this.lastName, this.firstName, this.address, this.eMail, phone);
    }
}
