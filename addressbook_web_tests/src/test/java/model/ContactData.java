package model;

public record ContactData(String lastName, String firstName, String address, String eMail, String phone) {
    public ContactData() {
        this("", "", "", "", "");
    }
}
