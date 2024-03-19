package model;

public record ContactData(String id, String lastName,String middlename, String firstName, String address, String eMail, String phone) {
    public ContactData() {
        this("", "", "", "", "", "","");
    }

    public ContactData withId(String id) {
        return new ContactData(id, this.lastName,this.middlename, this.firstName, this.address, this.eMail, this.phone);
    }

    public ContactData withLastName(String lastName) {
        return new ContactData(this.id, lastName,this.middlename, this.firstName, this.address, this.eMail, this.phone);
    }

    public ContactData withFirstName(String firstName) {
        return new ContactData(this.id, this.lastName,this.middlename, firstName, this.address, this.eMail, this.phone);
    }

    public ContactData withAddress(String address) {
        return new ContactData(this.id, this.lastName,this.middlename, this.firstName, address, this.eMail, this.phone);
    }

    public ContactData withEmail(String eMail) {
        return new ContactData(this.id, this.lastName,this.middlename, this.firstName, this.address, eMail, this.phone);
    }

    public ContactData withPhone(String phone) {
        return new ContactData(this.id, this.lastName,this.middlename, this.firstName, this.address, this.eMail, phone);
    }
    public ContactData withPhoto(String photo) {
        return new ContactData(this.id, this.lastName,this.middlename, this.firstName, this.address, this.eMail, this.phone);
    }
}
