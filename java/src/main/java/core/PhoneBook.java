package core;

import java.util.List;
import java.util.stream.Collectors;

public class PhoneBook {

    public List<Contact> getContacts() {
        return contacts;
    }

    private List<Contact> contacts;

    public PhoneBook(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public String reverseLookup(String phoneNumber) {
        return contacts.stream()
                .filter(contact -> phoneNumber.equals(contact.getPhoneNumber()))
                .findFirst()
                .map(Contact::getName)
                .orElse(null);
    }

    public String lookup(String name) {
        return contacts.stream()
                .filter(contact -> name.equalsIgnoreCase(contact.getName()))
                .findFirst()
                .map(Contact::getPhoneNumber)
                .orElse(null);
    }

    public List<Contact> findAllContact(String searchParameter) {
        if (searchParameter.isBlank()) {
            return contacts;
        }
        return contacts.stream()
                .filter(contact -> contact.getName().contains(searchParameter) ||
                        contact.getPhoneNumber().contains(searchParameter))
                .collect(Collectors.toList());
    }

    public boolean addContact(Contact newContact) {
        if (contacts.contains(newContact)) {
            return false;
        }
        contacts.add(newContact);
        return true;
    }

    public boolean removeContact(Contact contactToRemove) {
        if (contacts.contains(contactToRemove)) {
            return false;
        }
        contacts.remove(contactToRemove);
        return true;
    }

}