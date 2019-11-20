import Contact from "./Contact";

export default class PhoneBook {

    public get getContacts() :Array<Contact> {
        return contacts;
    }

    public constructor(private contacts: Array<Contact>) {
    }

    public reverseLookup( phoneNumber:string):string {
        return this.contacts
                .filter(contact => phoneNumber.equals(contact.getPhoneNumber()))
                .findFirst()
                .map(Contact.getName)
                .orElse(null);
    }

    public lookup(name : string) :string {
        return this.contacts.filter(contact => name.equalsIgnoreCase(contact.getName()))
                .findFirst()
                .map(Contact.getPhoneNumber)
                .orElse(null);
    }

    public findAllContact ( searchParameter: String): Array<Contact> {
        if (searchParameter.isBlank()) {
            return this.contacts;
        }
        return this.contacts
                .filter(contact => contact.getName().contains(searchParameter) ||
                        contact.getPhoneNumber().contains(searchParameter));
    }

    public addContact( newContact:Contact) : boolean{
        if (this.contacts.contains(newContact)) {
            return false;
        }
        this.contacts.push(newContact);
        return true;
    }

    public removeContact( contactToRemove: Contact) :boolean {
        if (this.contacts.find(contactToRemove)) {
            return false;
        }
        this.contacts.remove(contactToRemove);
        return true;
    }

}