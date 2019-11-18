package core;

import java.util.List;

public class PhoneBook {

    private List<Contact> contacts;

    public PhoneBook(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public String findName(String phoneNumber) {

        return contacts.stream()
                .filter(contact -> phoneNumber.equals(contact.getPhoneNumber()))
                .findFirst()
                .map(Contact::getName)
                .orElse(null);
    }

}